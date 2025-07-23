package com.hidirektor.lingo44;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingo44.UI.Screens.Authentication.LoginActivity;
import com.hidirektor.lingo44.UI.Screens.Dashboard.DashboardActivity;
import com.hidirektor.lingo44.UI.Screens.Welcome.WelcomeActivity;
import com.hidirektor.lingo44.Utility.Preferences.Language.LanguageUtil;
import com.hidirektor.lingo44.Utility.SystemDefaults;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide navigation and status bar immediately
        getWindow().getDecorView().setSystemUiVisibility(
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                        | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        LanguageUtil.setLanguage(getApplicationContext(), "tr");

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                handleBackPress();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                            | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
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