package com.hidirektor.lingify.Utility.Preferences.Language;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.hidirektor.lingify.Utility.SystemDefaults;

import java.util.Locale;

public class LanguageUtil {

    public static void setLanguage(Context context, String languageCode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SystemDefaults.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SystemDefaults.KEY_LANGUAGE, languageCode);
        editor.apply();

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static void loadLanguage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SystemDefaults.PREFS_NAME, Context.MODE_PRIVATE);
        String languageCode = sharedPreferences.getString(SystemDefaults.KEY_LANGUAGE, Locale.getDefault().getLanguage());
        setLanguage(context, languageCode);
    }
}