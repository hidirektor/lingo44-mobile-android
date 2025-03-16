package com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel.Exam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class LevelExamActivity extends AppCompatActivity {

    private ImageView backButton;
    private ImageView themeChangerButton;
    private Button startExamButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detect_exam);

        componentInitialize();

        backButton.setOnClickListener(v -> finish());
        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(this));
    }

    private void componentInitialize() {
        backButton = findViewById(R.id.backButton);
        themeChangerButton = findViewById(R.id.themeChangerButton);
        startExamButton = findViewById(R.id.startExamButton);
    }
}