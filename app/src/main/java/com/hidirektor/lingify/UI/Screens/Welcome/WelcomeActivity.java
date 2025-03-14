package com.hidirektor.lingify.UI.Screens.Welcome;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;
import com.hidirektor.lingify.Utility.SystemDefaults;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView themeChangerButton;
    private ImageView appLogo;
    private ImageView welcomeStockImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        componentInitialize();

        getSharedPreferences(SystemDefaults.PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putBoolean(SystemDefaults.KEY_FIRST_TIME, false)
                .apply();

        themeChangerButton.setOnClickListener(v -> toggleTheme());
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
        appLogo = findViewById(R.id.appLogo);
        welcomeStockImage = findViewById(R.id.welcomeStockImage);
    }

    private void toggleTheme() {
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        int newMode = (currentMode == AppCompatDelegate.MODE_NIGHT_NO)
                ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO;

        // Tema değiştir
        ThemeUtil.setTheme(WelcomeActivity.this, newMode);
        updateDrawablesBasedOnTheme();
    }

    private void updateDrawablesBasedOnTheme() {
        if (getResources().getConfiguration().uiMode == Configuration.UI_MODE_NIGHT_YES) {
            appLogo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.logo_kusadasi_dil_dark));
            welcomeStockImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.image_welcome_dark));
            themeChangerButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_sun));
            Log.d("TEST213", "DARK");
        } else {
            appLogo.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.logo_kusadasi_dil_white));
            welcomeStockImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.image_welcome_white));
            themeChangerButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_moon));
            Log.d("TEST213", "LIGHT");
        }
    }
}