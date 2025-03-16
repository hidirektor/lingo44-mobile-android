package com.hidirektor.lingify.Utility.Models.PersonalSetup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.PersonalSetup.PersonalSetupModel;
import com.hidirektor.lingify.Utility.Preferences.SPUtil;

import java.util.ArrayList;

public class PersonalSetupAdapter extends ArrayAdapter<PersonalSetupModel> {

    private Context context;

    public PersonalSetupAdapter(Context context, ArrayList<PersonalSetupModel> dataList) {
        super(context, 0, dataList);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_personal_setup_question, parent, false);
        }

        PersonalSetupModel data = getItem(position);

        TextView questionTextView = convertView.findViewById(R.id.questionTextView);
        ListView answerListView = convertView.findViewById(R.id.answerListView);

        assert data != null;
        questionTextView.setText(data.getQuestion());

        int selectedAnswerPosition = SPUtil.getUserAnswerPos(context, position);

        PersonalSetupAnswerAdapter answerAdapter = new PersonalSetupAnswerAdapter(context, data.getAnswerList(), position);
        answerAdapter.setSelectedPosition(selectedAnswerPosition);
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
