package com.hidirektor.lingify.UI.Screens.Setup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;
import com.hidirektor.lingify.Utility.SystemDefaults;

import org.json.JSONException;
import org.json.JSONObject;

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

        SharedPreferences sharedPreferences = getSharedPreferences(SystemDefaults.PREFS_NAME, MODE_PRIVATE);
        String jsonData = sharedPreferences.getString(SystemDefaults.KEY_USER_SETUP, "{}");

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");

            String selectedCourse = firstSetup.getString("selectedCourse");
            String selectedLanguage = firstSetup.getString("selectedLanguage");

            // Textleri güncelle
            firstQuestionFirstAnswer = findViewById(R.id.firstQuestionFirstAnswer);
            firstQuestionSecondAnswer = findViewById(R.id.firstQuestionSecondAnswer);
            secondQuestion = findViewById(R.id.secondQuestion);

            String firstAnswerText = getString(R.string.personal_setup_first_answer_1).replace("%selected_language%", selectedLanguage);
            String secondAnswerText = getString(R.string.personal_setup_first_answer_2).replace("%selected_language%", selectedLanguage);
            String secondQuestionText = getString(R.string.personal_setup_second_question).replace("%selected_course%", selectedCourse);

            firstQuestionFirstAnswer.setText(firstAnswerText);
            firstQuestionSecondAnswer.setText(secondAnswerText);
            secondQuestion.setText(secondQuestionText);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
