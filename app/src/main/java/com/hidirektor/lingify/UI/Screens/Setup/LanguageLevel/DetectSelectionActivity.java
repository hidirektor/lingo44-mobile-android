package com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class DetectSelectionActivity extends AppCompatActivity {

    private ImageView themeChangerButton;
    private Button pickLevelButton;
    private LinearLayout startExamButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detect_selection);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> {
            ThemeUtil.changeTheme(DetectSelectionActivity.this);
        });
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
        pickLevelButton = findViewById(R.id.pickLevelButton);
        startExamButton = findViewById(R.id.startExamButton);
    }
}
