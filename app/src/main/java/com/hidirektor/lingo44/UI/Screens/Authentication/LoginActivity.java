package com.hidirektor.lingo44.UI.Screens.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.hidirektor.lingo44.BaseActivity;
import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.UI.Screens.Authentication.Register.RegisterActivity;
import com.hidirektor.lingo44.UI.Screens.Home.HomeActivity;
import com.hidirektor.lingo44.Utility.Preferences.Theme.ThemeUtil;

public class LoginActivity extends BaseActivity {

    private ImageView themeChangerButton;

    private TextView loginWithPhoneNumber;
    private TextInputEditText emailInputField;
    private PinView passwordPinView;

    private Button loginButton;

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
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);

        loginWithPhoneNumber = findViewById(R.id.loginWithPhoneNumber);
        emailInputField = findViewById(R.id.emailInputField);
        passwordPinView = findViewById(R.id.passwordPinView);

        loginButton = findViewById(R.id.loginButton);

        registerText = findViewById(R.id.registerText);
    }
}
