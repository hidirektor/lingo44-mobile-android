package com.hidirektor.lingify.Utility.Models.LevelExam.Basic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.LevelExam.Basic.QuestionModel;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<QuestionModel> {

    private List<QuestionModel> questions;
    private Context context;

    public QuestionAdapter(Context context, List<QuestionModel> questions) {
        super(context, R.layout.list_item_exam_questions, questions);
        this.context = context;
        this.questions = questions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_exam_questions, parent, false);
        }

        final QuestionModel question = getItem(position);  // Mark it as final

        TextView questionText = convertView.findViewById(R.id.questionText);
        RadioGroup answersGroup = convertView.findViewById(R.id.answersGroup);
        RadioButton optionA = convertView.findViewById(R.id.optionA);
        RadioButton optionB = convertView.findViewById(R.id.optionB);
        RadioButton optionC = convertView.findViewById(R.id.optionC);

        questionText.setText(question.getQuestionText());
        optionA.setText(question.getAnswers()[0]);
        optionB.setText(question.getAnswers()[1]);
        optionC.setText(question.getAnswers()[2]);

        // Set selected answer (if any)
        if (question.getSelectedAnswer() != null) {
            if (question.getSelectedAnswer().equals(question.getAnswers()[0])) {
                optionA.setChecked(true);
            } else if (question.getSelectedAnswer().equals(question.getAnswers()[1])) {
                optionB.setChecked(true);
            } else if (question.getSelectedAnswer().equals(question.getAnswers()[2])) {
                optionC.setChecked(true);
            }
        }

        answersGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = parent.findViewById(checkedId);
            if (selectedRadioButton != null) {
                question.setSelectedAnswer(selectedRadioButton.getText().toString());
            }
        });

        return convertView;
    }
}