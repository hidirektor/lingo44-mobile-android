package com.hidirektor.lingify.UI.Screens.Setup;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Preferences.Theme.ThemeUtil;

public class PersonalSetupActivity extends AppCompatActivity {

    private ImageView themeChangerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setup);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> ThemeUtil.changeTheme(PersonalSetupActivity.this));
    }

    private void componentInitialize() {
        themeChangerButton = findViewById(R.id.themeChangerButton);
    }
}
