package com.hidirektor.lingo44;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.hidirektor.lingo44.UI.Screens.Loading.LoadingActivity;
import com.hidirektor.lingo44.Utility.Preferences.Language.LanguageUtil;
import com.hidirektor.lingo44.Utility.Preferences.Theme.ThemeUtil;

public class SplashActivity extends BaseActivity {

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
