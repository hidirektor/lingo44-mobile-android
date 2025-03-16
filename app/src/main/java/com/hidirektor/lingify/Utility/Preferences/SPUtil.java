package com.hidirektor.lingify.Utility.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.SystemDefaults;

import org.json.JSONException;
import org.json.JSONObject;

public class SPUtil {

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SystemDefaults.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void saveUserSetup(Context context, String selectedCourse) {
        String selectedLanguage;
        if (selectedCourse.contains("IELTS") || selectedCourse.contains("TOEFL")) {
            selectedLanguage = context.getString(R.string.language_english);
        } else if (selectedCourse.contains("Goethe")) {
            selectedLanguage = context.getString(R.string.language_german);
        } else {
            selectedLanguage = context.getString(R.string.language_english);
        }

        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        String jsonData = "{ \"firstSetup\": { " +
                "\"selectedCourse\": \"" + selectedCourse + "\", " +
                "\"selectedLanguage\": \"" + selectedLanguage + "\", " +
                "\"firstAnswer\": \"\", " +
                "\"firstAnswerPosition\": \"-1\", " +
                "\"secondAnswer\": \"\", " +
                "\"secondAnswerPosition\": \"-1\", " +
                "\"moreDetail\": \"\" " +
                "} }";
        editor.putString(SystemDefaults.KEY_USER_SETUP, jsonData);
        editor.apply();
    }

    public static void saveAnswer(Context context, String selectedAnswer, int questionPosition, int answerPosition) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        String jsonData = getUserSetupJson(context);
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");

            if (questionPosition == 0) {
                firstSetup.put("firstAnswer", selectedAnswer);
                firstSetup.put("firstAnswerPosition", answerPosition);
            } else if (questionPosition == 1) {
                firstSetup.put("secondAnswer", selectedAnswer);
                firstSetup.put("secondAnswerPosition", answerPosition);
            }

            editor.putString(SystemDefaults.KEY_USER_SETUP, jsonObject.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getUserSetupJson(Context context) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getString(SystemDefaults.KEY_USER_SETUP, "{}");
    }

    public static String getSelectedCourse(Context context) {
        SharedPreferences prefs = getSharedPreferences(context);
        String jsonData = prefs.getString(SystemDefaults.KEY_USER_SETUP, "{}");
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");
            return firstSetup.getString("selectedCourse");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSelectedLanguage(Context context) {
        SharedPreferences prefs = getSharedPreferences(context);
        String jsonData = prefs.getString(SystemDefaults.KEY_USER_SETUP, "{}");
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");
            return firstSetup.getString("selectedLanguage");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUserAnswer(Context context, int questionPosition) {
        SharedPreferences prefs = getSharedPreferences(context);
        String jsonData = prefs.getString(SystemDefaults.KEY_USER_SETUP, "{}");
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");
            if (questionPosition == 0) {
                return firstSetup.getString("firstAnswer");
            } else if (questionPosition == 1) {
                return firstSetup.getString("secondAnswer");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getUserAnswerPos(Context context, int questionPosition) {
        SharedPreferences prefs = getSharedPreferences(context);
        String jsonData = prefs.getString(SystemDefaults.KEY_USER_SETUP, "{}");
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");
            if (questionPosition == 0) {
                return firstSetup.getInt("firstAnswerPosition");
            } else if (questionPosition == 1) {
                return firstSetup.getInt("secondAnswerPosition");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }
}