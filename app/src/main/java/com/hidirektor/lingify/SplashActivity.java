package com.hidirektor.lingify;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.UI.Screens.Loading.LoadingActivity;
import com.hidirektor.lingify.Utility.Preferences.Language.LanguageUtil;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LanguageUtil.loadLanguage(this);
        ThemeUtil.loadTheme(this);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoadingActivity.class));
            finish();
        }, 500);
    }
}
