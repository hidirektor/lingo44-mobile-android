package com.hidirektor.lingo44.UI.Screens.Setup.LanguageLevel.Exam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.hidirektor.lingo44.BaseActivity;
import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.Utility.Preferences.Theme.ThemeUtil;

public class LevelExamActivity extends BaseActivity {

    private ImageView backButton;
    private ImageView themeChangerButton;
    private Button startExamButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_level_detect_exam_warning);

        componentInitialize();

        backButton.setOnClickListener(v -> finish());
        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(this));
        startExamButton.setOnClickListener(v -> {

        });
    }

    private void componentInitialize() {
        backButton = findViewById(R.id.backButton);
        themeChangerButton = findViewById(R.id.themeChangerButton);
        startExamButton = findViewById(R.id.startExamButton);
    }
}