package com.hidirektor.lingify.UI.Screens.Authentication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class LoginActivity extends AppCompatActivity {

    private ImageView themeChangerButton;

    private TextView loginWithPhoneNumber;
    private TextInputEditText emailInputField;

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
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);

        loginWithPhoneNumber = findViewById(R.id.loginWithPhoneNumber);
        emailInputField = findViewById(R.id.emailInputField);

        loginButton = findViewById(R.id.loginButton);

        loginViaGoogleLayout = findViewById(R.id.loginViaGoogleLayout);
        loginViaAppleLayout = findViewById(R.id.loginViaAppleLayout);

        registerText = findViewById(R.id.registerText);
    }
}
