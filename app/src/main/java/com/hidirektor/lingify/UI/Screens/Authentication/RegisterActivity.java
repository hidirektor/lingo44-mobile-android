package com.hidirektor.lingify.UI.Screens.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.UI.Screens.Setup.CourseSelectionActivity;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class RegisterActivity extends AppCompatActivity {

    private ImageView backButton;
    private ImageView themeChangerButton;

    private TextInputEditText usernameInputField;
    private TextInputEditText nameSurnameInputField;
    private TextInputEditText emailInputField;
    private CountryCodePicker countryCodePicker;
    private TextInputEditText phoneNumberInputField;

    private Button registerButton;
    private LinearLayout registerViaGoogleLayout;
    private LinearLayout registerViaAppleLayout;

    private PinView passwordPinView;
    private PinView confirmPasswordPinView;

    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        componentInitialize();

        backButton.setOnClickListener(v -> finish());
        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(RegisterActivity.this));
        loginText.setOnClickListener(v -> {
            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });

        registerButton.setOnClickListener(v -> {
            Intent courseSelectionIntent = new Intent(RegisterActivity.this, CourseSelectionActivity.class);
            startActivity(courseSelectionIntent);
            finish();
        });
    }

    private void componentInitialize() {
        backButton = findViewById(R.id.backButton);
        themeChangerButton = findViewById(R.id.themeChangerButton);

        usernameInputField = findViewById(R.id.usernameInputField);
        nameSurnameInputField = findViewById(R.id.nameSurnameInputField);
        emailInputField = findViewById(R.id.emailInputField);
        countryCodePicker = findViewById(R.id.countryCodePicker);
        phoneNumberInputField = findViewById(R.id.phoneNumberInputField);
        passwordPinView = findViewById(R.id.passwordPinView);
        confirmPasswordPinView = findViewById(R.id.confirmPasswordPinView);

        registerButton = findViewById(R.id.registerButton);
        registerViaGoogleLayout = findViewById(R.id.registerViaGoogleLayout);
        registerViaAppleLayout = findViewById(R.id.registerViaAppleLayout);
        loginText = findViewById(R.id.loginText);
    }
}
