package com.hidirektor.lingify.UI.Screens.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.hidirektor.lingify.BaseActivity;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.UI.Screens.Authentication.Register.RegisterActivity;
import com.hidirektor.lingify.UI.Screens.Dashboard.DashboardActivity;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class LoginActivity extends BaseActivity {

    private ImageView themeChangerButton;

    private TextView loginWithPhoneNumber;
    private TextInputEditText emailInputField;
    private PinView passwordPinView;

    private Button loginButton;

    private LinearLayout loginViaGoogleLayout;
    private LinearLayout loginViaAppleLayout;

    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(LoginActivity.this));

        registerText.setOnClickListener(v -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });

        loginButton.setOnClickListener(v -> {
            Intent dashboardIntent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(dashboardIntent);
            finish();
        });
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);

        loginWithPhoneNumber = findViewById(R.id.loginWithPhoneNumber);
        emailInputField = findViewById(R.id.emailInputField);
        passwordPinView = findViewById(R.id.passwordPinView);

        loginButton = findViewById(R.id.loginButton);

        loginViaGoogleLayout = findViewById(R.id.loginViaGoogleLayout);
        loginViaAppleLayout = findViewById(R.id.loginViaAppleLayout);

        registerText = findViewById(R.id.registerText);
    }
}
