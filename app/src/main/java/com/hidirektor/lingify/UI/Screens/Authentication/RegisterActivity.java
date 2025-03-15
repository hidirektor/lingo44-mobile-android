package com.hidirektor.lingify.UI.Screens.Authentication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class RegisterActivity extends AppCompatActivity {

    private ImageView backButton;
    private ImageView themeChangerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        componentInitialize();

        backButton.setOnClickListener(v -> finish());
        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(RegisterActivity.this));
    }

    private void componentInitialize() {
        backButton = findViewById(R.id.backButton);
        themeChangerButton = findViewById(R.id.themeChangerButton);
    }
}
