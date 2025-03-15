package com.hidirektor.lingify.UI.Screens.Welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.UI.Screens.Authentication.LoginActivity;
import com.hidirektor.lingify.UI.Screens.Setup.CourseSelectionActivity;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;
import com.hidirektor.lingify.Utility.SystemDefaults;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView themeChangerButton;
    private Button getReadyButton;

    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        componentInitialize();

        getSharedPreferences(SystemDefaults.PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putBoolean(SystemDefaults.KEY_FIRST_TIME, false)
                .apply();

        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(WelcomeActivity.this));
        getReadyButton.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, CourseSelectionActivity.class);
            startActivity(intent);
        });
        /*
        TODO
        burası register'a atacak register'dan sonra course selection'a gönder
         */

        loginText.setOnClickListener(v -> {
            Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
        getReadyButton = findViewById(R.id.getReadyButton);

        loginText = findViewById(R.id.loginText);
    }
}