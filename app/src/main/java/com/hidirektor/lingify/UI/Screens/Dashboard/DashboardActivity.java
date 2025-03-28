package com.hidirektor.lingify.UI.Screens.Dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.hidirektor.lingify.BaseActivity;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.VoiceRoom.Adapter.VoiceRoomAdapter;
import com.hidirektor.lingify.Utility.Models.VoiceRoom.VoiceRoom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DashboardActivity extends BaseActivity {

    private ConstraintLayout homeButton;
    private ConstraintLayout podcastButton;
    private FloatingActionButton dictionaryButton;
    private ConstraintLayout practicesButton;
    private ConstraintLayout statsButton;

    private TabLayout tabLayout;
    private FrameLayout selectedScreen;
    private ListView voiceRoomList;

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
        selectedScreen = findViewById(R.id.selectedScreen);
        voiceRoomList = findViewById(R.id.voiceRoomList);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.dashboard_main_section)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.dashboard_speaking_club_section)));

        setupTabLayoutListener();
        setupVoiceRoomList();
    }

    private void setupTabLayoutListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    selectedScreen.setVisibility(View.VISIBLE);
                } else {
                    selectedScreen.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void setupVoiceRoomList() {
        List<VoiceRoom> roomList = new ArrayList<>();

        // Get current time
        long currentTimeMillis = System.currentTimeMillis();

        // Add voice rooms with dynamic start and end times (2 hours from now)
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(currentTimeMillis));

        // 2 hours later
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        calendar.add(Calendar.MINUTE, 2);
        String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(calendar.getTime());

        // Adding rooms to the list
        roomList.add(new VoiceRoom("English Beginners", "J.D. Dil Kursu", "https://cdn-icons-png.flaticon.com/512/0/747.png",
                startDate, endDate, 15, 120, true, "https://www.fda.gov/files/iStock-157317886.jpg"));
        roomList.add(new VoiceRoom("IELTS Practice", "J.S. Dil Kursu", "https://cdn-icons-png.flaticon.com/512/0/747.png",
                startDate, endDate, 8, 120, false, "https://www.fda.gov/files/iStock-157317886.jpg"));

        VoiceRoomAdapter adapter = new VoiceRoomAdapter(this, roomList);
        voiceRoomList.setAdapter(adapter);
    }
}