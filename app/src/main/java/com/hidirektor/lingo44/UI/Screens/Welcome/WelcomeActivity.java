package com.hidirektor.lingo44.UI.Screens.Welcome;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hidirektor.lingo44.BaseActivity;
import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.Utility.Preferences.Theme.ThemeUtil;
import com.hidirektor.lingo44.Utility.SystemDefaults;

import java.util.Calendar;

public class WelcomeActivity extends BaseActivity {

    private ImageView themeChangerButton;
    private TextView greetingText;

    private Button loginButton;
    private LinearLayout forgetPasswordButton;
    private LinearLayout registerButton;

    private LinearLayout detectLevelButton;
    private LinearLayout ieltsButton;
    private FloatingActionButton dictionaryButton;
    private LinearLayout arcadeButton;
    private LinearLayout contactUsButton;

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

        loginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(WelcomeActivity.this, com.hidirektor.lingo44.UI.Screens.Authentication.LoginActivity.class);
            startActivity(loginIntent);
        });

        registerButton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(WelcomeActivity.this, com.hidirektor.lingo44.UI.Screens.Authentication.Register.RegisterActivity.class);
            startActivity(registerIntent);
        });

        // Bottom bar button logic
        FrameLayout detectLevelButton = findFrameLayoutForTextView(R.id.detectYourLevelText);
        FrameLayout ieltsButton = findFrameLayoutForTextView(R.id.startIELTSExamText);
        FrameLayout arcadeButton = findFrameLayoutForTextView(R.id.minigameText);
        FrameLayout contactUsButton = findFrameLayoutForTextView(R.id.contactUsText);

        FrameLayout[] bottomButtons = {detectLevelButton, ieltsButton, arcadeButton, contactUsButton};
        int[] toastStrings = {R.string.welcome_button, R.string.bottom_nav_ielts_trial, R.string.bottom_nav_arcade, R.string.bottom_nav_contact_us};
        for (int i = 0; i < bottomButtons.length; i++) {
            FrameLayout btn = bottomButtons[i];
            if (btn != null) {
                // Add ripple effect if not already present
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int rippleColor = getResources().getColor(R.color.body_secondary, null);
                    RippleDrawable rippleDrawable = new RippleDrawable(
                            ColorStateList.valueOf(rippleColor),
                            btn.getBackground(),
                            null
                    );
                    btn.setBackground(rippleDrawable);
                }
                btn.setClickable(true);
                btn.setFocusable(true);
                int toastString = toastStrings[i];
                btn.setOnClickListener(v -> Toast.makeText(this, getString(toastString), Toast.LENGTH_SHORT).show());
            }
        }
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
        greetingText = findViewById(R.id.greetingText);

        loginButton = findViewById(R.id.loginButton);
        forgetPasswordButton = findViewById(R.id.forgetPasswordLayout);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        registerButton = findViewById(R.id.registerLayout);

        detectLevelButton = findViewById(R.id.detectLevelButton);
        ieltsButton = findViewById(R.id.ieltsButton);
        dictionaryButton = findViewById(R.id.dictionaryButton);
        arcadeButton = findViewById(R.id.arcadeButton);
        contactUsButton = findViewById(R.id.contactUsButton);

        // Set greeting based on time
        String greeting = getGreetingByTime();
        greetingText.setText(greeting);

        // Set underline for forgot password text
        String forgotText = getString(R.string.forgot_password);
        forgotPasswordText.setText(Html.fromHtml("<u>" + forgotText + "</u>"));
    }

    // Utility: Find the FrameLayout parent of a TextView by its ID
    private FrameLayout findFrameLayoutForTextView(int textViewId) {
        TextView tv = findViewById(textViewId);
        if (tv != null && tv.getParent() instanceof LinearLayout) {
            LinearLayout ll = (LinearLayout) tv.getParent();
            for (int i = 0; i < ll.getChildCount(); i++) {
                if (ll.getChildAt(i) instanceof FrameLayout) {
                    return (FrameLayout) ll.getChildAt(i);
                }
            }
        }
        return null;
    }

    private String getGreetingByTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 12) {
            return getString(R.string.good_morning);
        } else if (hour >= 12 && hour < 18) {
            return getString(R.string.good_afternoon);
        } else if (hour >= 18 && hour < 23) {
            return getString(R.string.good_evening);
        } else {
            return getString(R.string.good_night);
        }
    }
}