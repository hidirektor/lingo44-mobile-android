package com.hidirektor.lingo44.Utility.Models.PersonalSetup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.Utility.Models.PersonalSetup.PersonalSetupAnswerModel;
import com.hidirektor.lingo44.Utility.Preferences.SPUtil;

import java.util.LinkedList;

public class PersonalSetupAnswerAdapter extends ArrayAdapter<PersonalSetupAnswerModel> {

    private Context context;
    private int selectedPosition;
    private int questionPosition;

    public PersonalSetupAnswerAdapter(Context context, LinkedList<PersonalSetupAnswerModel> answers, int questionPosition) {
        super(context, 0, answers);
        this.context = context;

        this.questionPosition = questionPosition;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_personal_setup_answer, parent, false);
        }

        PersonalSetupAnswerModel answer = getItem(position);

        LinearLayout mainItemLayout = convertView.findViewById(R.id.mainItemLayout);
        ImageView answerIcon = convertView.findViewById(R.id.answerIcon);
        TextView answerTextView = convertView.findViewById(R.id.answerDesc);

        answerIcon.setImageResource(answer.getIconForCurrentTheme());
        answerTextView.setText(answer.getAnswer());

        if (position == selectedPosition) {
            mainItemLayout.setBackgroundResource(R.drawable.background_item_answer_active);
        } else {
            mainItemLayout.setBackgroundResource(R.drawable.background_item_answer_not_active);
        }

        mainItemLayout.setOnClickListener(v -> {
            if (selectedPosition == position) {
                selectedPosition = -1;
            } else {
                selectedPosition = position;
            }

            String selectedAnswer = answer.getAnswer();

            SPUtil.saveAnswer(context, selectedAnswer, questionPosition, selectedPosition);

            notifyDataSetChanged();
        });

        return convertView;
    }
}
