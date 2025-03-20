package com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hidirektor.lingify.BaseActivity;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel.Exam.LevelExamWarningBottomSheet;
import com.hidirektor.lingify.Utility.Models.LanguageLevel.LanguageLevelModel;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

import java.util.LinkedList;

public class DetectSelectionActivity extends BaseActivity {

    private ImageView themeChangerButton;
    private Button pickLevelButton;
    private LinearLayout startExamButton;

    //ListView variables
    LinkedList<LanguageLevelModel> languageLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detect_selection);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> {
            ThemeUtil.changeTheme(DetectSelectionActivity.this);
        });

        pickLevelButton.setOnClickListener(v -> showLevelSelectionPopup());

        startExamButton.setOnClickListener(v -> {
            LevelExamWarningBottomSheet bottomSheetExam = new LevelExamWarningBottomSheet();
            bottomSheetExam.show(getSupportFragmentManager(), bottomSheetExam.getTag());
        });
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
        languageLevels = new LinkedList<>();
        languageLevels.add(new LanguageLevelModel(level1, english));
        languageLevels.add(new LanguageLevelModel(level2, english));
        languageLevels.add(new LanguageLevelModel(level3, english));
        languageLevels.add(new LanguageLevelModel(level4, english));
        languageLevels.add(new LanguageLevelModel(level5, english));
        languageLevels.add(new LanguageLevelModel(level6, english));
    }

    private void showLevelSelectionPopup() {
        LanguageLevelBottomSheet bottomSheetDialog = LanguageLevelBottomSheet.newInstance(languageLevels);
        bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
    }
}
