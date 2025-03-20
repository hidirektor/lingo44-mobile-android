package com.hidirektor.lingify.UI.Screens.Authentication.Register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import com.hidirektor.lingify.BaseActivity;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.UI.Screens.Setup.CourseSelectionActivity;
import com.hidirektor.lingify.Utility.Models.PersonalSetup.Adapter.PersonalSetupAdapter;
import com.hidirektor.lingify.Utility.Models.PersonalSetup.PersonalSetupAnswerModel;
import com.hidirektor.lingify.Utility.Models.PersonalSetup.PersonalSetupModel;
import com.hidirektor.lingify.Utility.Preferences.SPUtil;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

import java.util.ArrayList;
import java.util.LinkedList;

public class PersonalSetupActivity extends BaseActivity {

    private ImageView themeChangerButton;
    private Button registerButton;
    private TextInputEditText moreDetailInputField;

    private ListView personalSetupListView;
    private ArrayList<PersonalSetupModel> personalDataList;
    private PersonalSetupAdapter personalSetupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setup);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(PersonalSetupActivity.this));
        registerButton.setOnClickListener(v -> {
            String moreDetail = moreDetailInputField.getText() != null ? moreDetailInputField.getText().toString() : "";
            SPUtil.saveMoreDetail(PersonalSetupActivity.this, moreDetail);

            Intent courseSelectionIntent = new Intent(PersonalSetupActivity.this, CourseSelectionActivity.class);
            startActivity(courseSelectionIntent);
            finish();
        });
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
        registerButton = findViewById(R.id.registerButton);
        moreDetailInputField = findViewById(R.id.moreDetailInputField);

        personalSetupListView = findViewById(R.id.personalSetupListView);

        personalDataList = preparePersonalData();
        personalSetupAdapter = new PersonalSetupAdapter(this, personalDataList);
        personalSetupListView.setAdapter(personalSetupAdapter);
    }

    private ArrayList<PersonalSetupModel> preparePersonalData() {
        ArrayList<PersonalSetupModel> dataList = new ArrayList<>();
        String selectedLanguage = SPUtil.getSelectedLanguage(this);
        String selectedCourse = SPUtil.getSelectedCourse(this);

        // İlk Soru ve Cevaplar
        LinkedList<PersonalSetupAnswerModel> firstQuestionAnswers = new LinkedList<>();
        firstQuestionAnswers.add(new PersonalSetupAnswerModel(
                getString(R.string.personal_setup_first_answer_1).replace("%selected_language%", selectedLanguage),
                R.drawable.icon_flag_light, R.drawable.icon_flag_dark));
        firstQuestionAnswers.add(new PersonalSetupAnswerModel(
                getString(R.string.personal_setup_first_answer_2).replace("%selected_language%", selectedLanguage),
                R.drawable.icon_book_light, R.drawable.icon_book_dark));
        dataList.add(new PersonalSetupModel(getString(R.string.personal_setup_first_question), firstQuestionAnswers));

        // İkinci Soru ve Cevaplar
        LinkedList<PersonalSetupAnswerModel> secondQuestionAnswers = new LinkedList<>();
        secondQuestionAnswers.add(new PersonalSetupAnswerModel(
                getString(R.string.personal_setup_second_answer_1),
                R.drawable.icon_study_light, R.drawable.icon_study_dark));
        secondQuestionAnswers.add(new PersonalSetupAnswerModel(
                getString(R.string.personal_setup_second_answer_2),
                R.drawable.icon_work_light, R.drawable.icon_work_dark));
        secondQuestionAnswers.add(new PersonalSetupAnswerModel(
                getString(R.string.personal_setup_second_answer_3),
                R.drawable.icon_world_light, R.drawable.icon_world_dark));
        secondQuestionAnswers.add(new PersonalSetupAnswerModel(
                getString(R.string.personal_setup_second_answer_4),
                R.drawable.icon_flame_light, R.drawable.icon_flame_dark));
        secondQuestionAnswers.add(new PersonalSetupAnswerModel(
                getString(R.string.personal_setup_second_answer_5),
                R.drawable.icon_people_light, R.drawable.icon_people_dark));
        dataList.add(new PersonalSetupModel(
                getString(R.string.personal_setup_second_question).replace("%selected_course%", selectedCourse),
                secondQuestionAnswers));

        return dataList;
    }
}