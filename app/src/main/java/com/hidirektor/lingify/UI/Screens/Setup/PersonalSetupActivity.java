package com.hidirektor.lingify.UI.Screens.Setup;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.PersonalData.Adapter.PersonalSetupAdapter;
import com.hidirektor.lingify.Utility.Models.PersonalData.AnswerModel;
import com.hidirektor.lingify.Utility.Models.PersonalData.PersonalDataModel;
import com.hidirektor.lingify.Utility.Preferences.SPUtil;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

import java.util.ArrayList;
import java.util.LinkedList;

public class PersonalSetupActivity extends AppCompatActivity {

    private ImageView themeChangerButton;

    private ListView personalSetupListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setup);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(PersonalSetupActivity.this));
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);

        personalSetupListView = findViewById(R.id.personalSetupListView);

        ArrayList<PersonalDataModel> personalDataList = preparePersonalData();
        PersonalSetupAdapter adapter = new PersonalSetupAdapter(this, personalDataList);
        personalSetupListView.setAdapter(adapter);
    }

    private ArrayList<PersonalDataModel> preparePersonalData() {
        ArrayList<PersonalDataModel> dataList = new ArrayList<>();
        String selectedLanguage = SPUtil.getSelectedLanguage(this);
        String selectedCourse = SPUtil.getSelectedCourse(this);

        // İlk Soru ve Cevaplar
        LinkedList<AnswerModel> firstQuestionAnswers = new LinkedList<>();
        firstQuestionAnswers.add(new AnswerModel(
                getString(R.string.personal_setup_first_answer_1).replace("%selected_language%", selectedLanguage),
                R.drawable.icon_flag_light, R.drawable.icon_flag_dark));
        firstQuestionAnswers.add(new AnswerModel(
                getString(R.string.personal_setup_first_answer_2).replace("%selected_language%", selectedLanguage),
                R.drawable.icon_book_light, R.drawable.icon_book_dark));
        dataList.add(new PersonalDataModel(getString(R.string.personal_setup_first_question), firstQuestionAnswers));

        // İkinci Soru ve Cevaplar
        LinkedList<AnswerModel> secondQuestionAnswers = new LinkedList<>();
        secondQuestionAnswers.add(new AnswerModel(
                getString(R.string.personal_setup_second_answer_1),
                R.drawable.icon_study_light, R.drawable.icon_study_dark));
        secondQuestionAnswers.add(new AnswerModel(
                getString(R.string.personal_setup_second_answer_2),
                R.drawable.icon_work_light, R.drawable.icon_work_dark));
        secondQuestionAnswers.add(new AnswerModel(
                getString(R.string.personal_setup_second_answer_3),
                R.drawable.icon_world_light, R.drawable.icon_world_dark));
        secondQuestionAnswers.add(new AnswerModel(
                getString(R.string.personal_setup_second_answer_4),
                R.drawable.icon_flame_light, R.drawable.icon_flame_dark));
        secondQuestionAnswers.add(new AnswerModel(
                getString(R.string.personal_setup_second_answer_5),
                R.drawable.icon_people_light, R.drawable.icon_people_dark));
        dataList.add(new PersonalDataModel(
                getString(R.string.personal_setup_second_question).replace("%selected_course%", selectedCourse),
                secondQuestionAnswers));

        return dataList;
    }
}
