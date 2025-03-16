package com.hidirektor.lingify.Utility.Models.PersonalData.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.PersonalData.PersonalDataModel;

import java.util.ArrayList;

public class PersonalSetupAdapter extends ArrayAdapter<PersonalDataModel> {
    public PersonalSetupAdapter(Context context, ArrayList<PersonalDataModel> dataList) {
        super(context, 0, dataList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_personal_setup_question, parent, false);
        }

        PersonalDataModel data = getItem(position);

        TextView questionTextView = convertView.findViewById(R.id.questionTextView);
        ListView answerListView = convertView.findViewById(R.id.answerListView);

        questionTextView.setText(data.getQuestion());

        AnswerAdapter answerAdapter = new AnswerAdapter(getContext(), data.getAnswerList());
        answerListView.setAdapter(answerAdapter);

        setListViewHeightBasedOnChildren(answerListView);

        return convertView;
    }

    private static void setListViewHeightBasedOnChildren(ListView listView) {
        ArrayAdapter<?> adapter = (ArrayAdapter<?>) listView.getAdapter();
        if (adapter == null) return;

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
