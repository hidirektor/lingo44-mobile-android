package com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.LanguageLevel.Adapter.LanguageLevelAdapter;
import com.hidirektor.lingify.Utility.Models.LanguageLevel.LanguageLevelModel;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

public class DetectSelectionActivity extends AppCompatActivity {

    private ImageView themeChangerButton;
    private Button pickLevelButton;
    private LinearLayout startExamButton;

    private PopupWindow levelPopupWindow;

    //ListView
    List<LanguageLevelModel> languageLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detect_selection);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> {
            ThemeUtil.changeTheme(DetectSelectionActivity.this);
        });

        pickLevelButton.setOnClickListener(v -> showLevelSelectionPopup());
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
        pickLevelButton = findViewById(R.id.pickLevelButton);
        startExamButton = findViewById(R.id.startExamButton);

        // Dil seviyelerini ve dilleri strings.xml'den al
        String english = getString(R.string.language_english);

        // Dil seviyelerini strings.xml'den al
        String level1 = getString(R.string.detect_level_popup_level_1);
        String level2 = getString(R.string.detect_level_popup_level_2);
        String level3 = getString(R.string.detect_level_popup_level_3);
        String level4 = getString(R.string.detect_level_popup_level_4);
        String level5 = getString(R.string.detect_level_popup_level_5);
        String level6 = getString(R.string.detect_level_popup_level_6);

        // Dil seviyelerini oluşturma
        languageLevels = new ArrayList<>();
        languageLevels.add(new LanguageLevelModel(level1, english));
        languageLevels.add(new LanguageLevelModel(level2, english));
        languageLevels.add(new LanguageLevelModel(level3, english));
        languageLevels.add(new LanguageLevelModel(level4, english));
        languageLevels.add(new LanguageLevelModel(level5, english));
        languageLevels.add(new LanguageLevelModel(level6, english));
    }

    private void showLevelSelectionPopup() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_level_selection, null);

        levelPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        levelPopupWindow.showAsDropDown(pickLevelButton, 0, 20);

        ListView languageLevelListView = popupView.findViewById(R.id.languageLevelListView);
        Button continueButton = popupView.findViewById(R.id.continueButton);

        LanguageLevelAdapter adapter = new LanguageLevelAdapter(this, languageLevels);
        languageLevelListView.setAdapter(adapter);

        continueButton.setOnClickListener(v -> {
            int selectedPosition = languageLevelListView.getCheckedItemPosition();
            if (selectedPosition != ListView.INVALID_POSITION) {
                LanguageLevelModel selectedLevel = (LanguageLevelModel) languageLevelListView.getItemAtPosition(selectedPosition);
                Log.d("LanguageLevel", "Selected Language Level: " + selectedLevel.getLevelName());
            }
        });
    }
}
