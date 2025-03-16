package com.hidirektor.lingify.UI.Screens.Setup;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.SPUtil;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class PersonalSetupActivity extends AppCompatActivity {

    private ImageView themeChangerButton;

    private TextView firstQuestionFirstAnswer;
    private TextView firstQuestionSecondAnswer;
    private TextView secondQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setup);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(PersonalSetupActivity.this));
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);

        firstQuestionFirstAnswer = findViewById(R.id.firstQuestionFirstAnswer);
        firstQuestionSecondAnswer = findViewById(R.id.firstQuestionSecondAnswer);
        secondQuestion = findViewById(R.id.secondQuestion);

        String selectedCourse = SPUtil.getSelectedCourse(this);
        String selectedLanguage = SPUtil.getSelectedLanguage(this);

        String firstAnswerText = getString(R.string.personal_setup_first_answer_1).replace("%selected_language%", selectedLanguage);
        String secondAnswerText = getString(R.string.personal_setup_first_answer_2).replace("%selected_language%", selectedLanguage);
        String secondQuestionText = getString(R.string.personal_setup_second_question).replace("%selected_course%", selectedCourse);

        firstQuestionFirstAnswer.setText(firstAnswerText);
        firstQuestionSecondAnswer.setText(secondAnswerText);
        secondQuestion.setText(secondQuestionText);
    }
}
