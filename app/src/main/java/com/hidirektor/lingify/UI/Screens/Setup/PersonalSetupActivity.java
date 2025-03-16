package com.hidirektor.lingify.UI.Screens.Setup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.PersonalData.Adapter.AnswerAdapter;
import com.hidirektor.lingify.Utility.Models.PersonalData.Adapter.PersonalSetupAdapter;
import com.hidirektor.lingify.Utility.Models.PersonalData.AnswerModel;
import com.hidirektor.lingify.Utility.Models.PersonalData.PersonalDataModel;
import com.hidirektor.lingify.Utility.Preferences.SPUtil;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class PersonalSetupActivity extends AppCompatActivity {

    private ImageView themeChangerButton;

    private ListView personalSetupListView;
    private ArrayList<PersonalDataModel> personalDataList;
    private PersonalSetupAdapter personalSetupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setup);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(PersonalSetupActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Seçili cevapları geri yükle
        setSelectedAnswers();
        Log.d("user setup", SPUtil.getUserSetupJson(this));
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);

        personalSetupListView = findViewById(R.id.personalSetupListView);

        personalDataList = preparePersonalData();
        personalSetupAdapter = new PersonalSetupAdapter(this, personalDataList);
        personalSetupListView.setAdapter(personalSetupAdapter);
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

    private void setSelectedAnswers() {
        if (personalDataList == null || personalSetupAdapter == null) {
            return; // Güvenlik kontrolü
        }

        // JSON'dan firstAnswer ve secondAnswer değerlerini al
        String jsonData = SPUtil.getUserSetupJson(this);
        String firstAnswer = "";
        String secondAnswer = "";
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");
            firstAnswer = firstSetup.optString("firstAnswer", ""); // Varsayılan boş string
            secondAnswer = firstSetup.optString("secondAnswer", ""); // Varsayılan boş string
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // personalDataList içindeki her bir PersonalDataModel'i kontrol et
        for (int i = 0; i < personalDataList.size(); i++) {
            PersonalDataModel dataModel = personalDataList.get(i);
            LinkedList<AnswerModel> answerList = dataModel.getAnswerList();

            // Hangi soruda olduğumuza göre cevapları kontrol et
            String targetAnswer = (i == 0) ? firstAnswer : secondAnswer;

            if (!targetAnswer.isEmpty()) {
                for (int j = 0; j < answerList.size(); j++) {
                    AnswerModel answer = answerList.get(j);
                    if (answer.getAnswer().equals(targetAnswer)) { // Tam eşleşme kontrolü
                        // AnswerAdapter'daki seçili pozisyonu güncelle
                        View listItem = personalSetupListView.getChildAt(i);
                        if (listItem != null) {
                            ListView answerListView = listItem.findViewById(R.id.answerListView);
                            AnswerAdapter answerAdapter = (AnswerAdapter) answerListView.getAdapter();
                            answerAdapter.setSelectedPosition(j); // Seçili pozisyonu ayarla
                            answerAdapter.notifyDataSetChanged(); // Görünümü güncelle
                            // SPUtil.saveAnswer(this, answer.getAnswer(), j); // Zaten kaydedilmiş, tekrar kaydetmeye gerek yok
                        }
                        break; // Eşleşmeyi bulduktan sonra iç döngüden çık
                    }
                }
            }
        }
    }
}
