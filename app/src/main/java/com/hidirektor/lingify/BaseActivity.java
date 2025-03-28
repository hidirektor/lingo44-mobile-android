package com.hidirektor.lingify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.UI.Screens.Authentication.LoginActivity;
import com.hidirektor.lingify.UI.Screens.Dashboard.DashboardActivity;
import com.hidirektor.lingify.UI.Screens.Welcome.WelcomeActivity;
import com.hidirektor.lingify.Utility.Preferences.Language.LanguageUtil;
import com.hidirektor.lingify.Utility.SystemDefaults;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LanguageUtil.setLanguage(getApplicationContext(), "tr");

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                handleBackPress();
            }
        });
    }

    private void handleBackPress() {
        SharedPreferences prefs = getSharedPreferences(SystemDefaults.PREFS_NAME, Context.MODE_PRIVATE);
        String token = prefs.getString(SystemDefaults.KEY_AUTH_TOKEN, null);
        boolean isFirstLaunch = prefs.getBoolean(SystemDefaults.KEY_FIRST_TIME, true);

        if (token != null) {
            navigateTo(DashboardActivity.class);
        } else {
            if (isFirstLaunch) {
                prefs.edit().putBoolean(SystemDefaults.KEY_FIRST_TIME, false).apply();
                navigateTo(WelcomeActivity.class);
            } else {
                navigateTo(LoginActivity.class);
            }
        }
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}