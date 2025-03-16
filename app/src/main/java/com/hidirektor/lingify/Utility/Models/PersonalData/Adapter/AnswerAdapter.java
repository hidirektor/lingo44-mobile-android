package com.hidirektor.lingify.Utility.Models.PersonalData.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.PersonalData.AnswerModel;

import java.util.LinkedList;

public class AnswerAdapter extends ArrayAdapter<AnswerModel> {
    public AnswerAdapter(Context context, LinkedList<AnswerModel> answers) {
        super(context, 0, answers);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_personal_setup_answer, parent, false);
        }

        AnswerModel answer = getItem(position);

        ImageView answerIcon = convertView.findViewById(R.id.answerIcon);
        TextView answerTextView = convertView.findViewById(R.id.answerDesc);

        answerIcon.setImageResource(answer.getIconForCurrentTheme());
        answerTextView.setText(answer.getAnswer());

        return convertView;
    }
}
