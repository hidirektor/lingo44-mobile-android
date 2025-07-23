package com.hidirektor.lingo44.UI.Screens.Welcome;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hidirektor.lingo44.BaseActivity;
import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.Utility.Preferences.Theme.ThemeUtil;
import com.hidirektor.lingo44.Utility.SystemDefaults;

import java.util.Calendar;

public class WelcomeActivity extends BaseActivity {

    private ImageView themeChangerButton;
    private Button getReadyButton;
    private Button registerButton;

    private TextView loginText;
    private TextView greetingText;
    private TextView welcomeSubText;
    private TextView forgotPasswordText;

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
        /*getReadyButton.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, CourseSelectionActivity.class);
            startActivity(intent);
        });*/
        /*registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
        });*/

        /*loginText.setOnClickListener(v -> {
            Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });*/
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
        //getReadyButton = findViewById(R.id.getReadyButton);
        registerButton = findViewById(R.id.registerButton);
        loginText = findViewById(R.id.loginText);
        greetingText = findViewById(R.id.greetingText);
        welcomeSubText = findViewById(R.id.welcomeSubText);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);

        // Set greeting based on time
        String greeting = getGreetingByTime();
        greetingText.setText(greeting);
        // welcomeSubText already set via XML to @string/personal_setup_title

        // Set underline for forgot password text
        String forgotText = getString(R.string.forgot_password);
        forgotPasswordText.setText(Html.fromHtml("<u>" + forgotText + "</u>"));
    }

    private String getGreetingByTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 12) {
            return "Günaydın";
        } else if (hour >= 12 && hour < 18) {
            return "İyi Günler";
        } else if (hour >= 18 && hour < 23) {
            return "İyi Akşamlar";
        } else {
            return "İyi Geceler";
        }
    }
}