package com.hidirektor.lingify.Utility.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String existingJson = sharedPreferences.getString(SystemDefaults.KEY_USER_SETUP, "{}");

        try {
            JSONObject rootObject = new JSONObject(existingJson);
            JSONObject firstSetup = rootObject.optJSONObject("firstSetup");
            if (firstSetup == null) {
                firstSetup = new JSONObject();
            }

            // Zorunlu alanları güncelle
            firstSetup.put("selectedCourse", selectedCourse);
            firstSetup.put("selectedLanguage", selectedLanguage);

            // Eksik alanları kontrol edip varsayılan değerleri ata
            if (!firstSetup.has("firstAnswer")) {
                firstSetup.put("firstAnswer", "");
            }
            if (!firstSetup.has("firstAnswerPosition")) {
                firstSetup.put("firstAnswerPosition", "-1");
            }
            if (!firstSetup.has("secondAnswer")) {
                firstSetup.put("secondAnswer", "");
            }
            if (!firstSetup.has("secondAnswerPosition")) {
                firstSetup.put("secondAnswerPosition", "-1");
            }
            if (!firstSetup.has("moreDetail")) {
                firstSetup.put("moreDetail", "");
            }

            rootObject.put("firstSetup", firstSetup);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SystemDefaults.KEY_USER_SETUP, rootObject.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveAnswer(Context context, String selectedAnswer, int questionPosition, int answerPosition) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        String jsonData = getUserSetupJson(context);
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject firstSetup = jsonObject.getJSONObject("firstSetup");

            if (questionPosition == 0) {
                firstSetup.put("firstAnswer", selectedAnswer);
                firstSetup.put("firstAnswerPosition", String.valueOf(answerPosition));
            } else if (questionPosition == 1) {
                firstSetup.put("secondAnswer", selectedAnswer);
                firstSetup.put("secondAnswerPosition", String.valueOf(answerPosition));
            }

            editor.putString(SystemDefaults.KEY_USER_SETUP, jsonObject.toString());
            editor.apply();

            Log.d("whole user after save", getUserSetupJson(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveMoreDetail(Context context, String moreDetail) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String existingJson = sharedPreferences.getString(SystemDefaults.KEY_USER_SETUP, "{}");

        try {
            JSONObject rootObject = new JSONObject(existingJson);
            JSONObject firstSetup = rootObject.optJSONObject("firstSetup");

            if (firstSetup == null) {
                firstSetup = new JSONObject();
            }

            firstSetup.put("moreDetail", moreDetail);
            rootObject.put("firstSetup", firstSetup);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SystemDefaults.KEY_USER_SETUP, rootObject.toString());
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
                return Integer.parseInt(firstSetup.getString("firstAnswerPosition"));
            } else if (questionPosition == 1) {
                return Integer.parseInt(firstSetup.getString("secondAnswerPosition"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return -1;
    }
}