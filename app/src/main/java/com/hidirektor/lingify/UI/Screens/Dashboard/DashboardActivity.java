package com.hidirektor.lingify.UI.Screens.Dashboard;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.hidirektor.lingify.BaseActivity;
import com.hidirektor.lingify.R;

public class DashboardActivity extends BaseActivity {

    private ConstraintLayout homeButton;
    private ConstraintLayout podcastButton;
    private FloatingActionButton dictionaryButton;
    private ConstraintLayout practicesButton;
    private ConstraintLayout statsButton;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        componentInitialize();
    }

    private void componentInitialize() {
        homeButton = findViewById(R.id.homeButton);
        podcastButton = findViewById(R.id.podcastButton);
        dictionaryButton = findViewById(R.id.dictionaryButton);
        practicesButton = findViewById(R.id.practicesButton);
        statsButton = findViewById(R.id.statsButton);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.dashboard_main_section)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.dashboard_speaking_club_section)));
    }
}
