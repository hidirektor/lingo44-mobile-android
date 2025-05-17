package com.hidirektor.lingo44.Utility.Models.VoiceRoom.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.Utility.Models.VoiceRoom.VoiceRoom;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class VoiceRoomAdapter extends ArrayAdapter<VoiceRoom> {

    public VoiceRoomAdapter(Context context, List<VoiceRoom> rooms) {
        super(context, 0, rooms);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_voice_room, parent, false);
        }

        VoiceRoom room = getItem(position);
        if (room == null) return convertView;

        // UI Elements
        ImageView adminImage = convertView.findViewById(R.id.adminImage);
        TextView adminName = convertView.findViewById(R.id.adminName);
        TextView roomSubject = convertView.findViewById(R.id.roomSubject);
        TextView participantsCount = convertView.findViewById(R.id.participantsCount);
        Button joinButton = convertView.findViewById(R.id.joinButton);
        ProgressBar timeRemainingBar = convertView.findViewById(R.id.timeRemainingBar);
        TextView startDate = convertView.findViewById(R.id.startDate);
        TextView endDate = convertView.findViewById(R.id.endDate);
        TextView remainTime = convertView.findViewById(R.id.remainTime);
        ImageView topicImage = convertView.findViewById(R.id.topicImage);

        adminName.setText(room.getAdminName());

        // Set room details
        SimpleDateFormat displayFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
        startDate.setText(getContext().getString(R.string.voice_room_start_date) + room.getStartDate());
        endDate.setText(getContext().getString(R.string.voice_room_end_date) + room.getEndDate());

        roomSubject.setText(room.getRoomSubject());
        participantsCount.setText(getContext().getString(R.string.voice_room_student) + ": " +
                room.getParticipantCount() + "/" + room.getMaxParticipants());

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                new int[] { R.attr.lingifyLogo, R.attr.lingifyErrorIcon }
        );

        // Load images with Glide
        Glide.with(getContext())
                .load(room.getAdminImageUrl())
                .placeholder(typedArray.getDrawable(0))
                .error(typedArray.getDrawable(1))
                .into(adminImage);

        Glide.with(getContext())
                .load(room.getTopicImageUrl())
                .placeholder(typedArray.getDrawable(0))
                .error(typedArray.getDrawable(1))
                .into(topicImage);

        long remainingTimeMillis = calculateRemainingTime(room.getStartDate(), room.getEndDate());
        int remainingTimeSeconds = (int) (remainingTimeMillis / 1000);
        timeRemainingBar.setMax(remainingTimeSeconds > 0 ? remainingTimeSeconds : 1);
        startCountdownTimer(remainingTimeMillis, timeRemainingBar, remainTime);

        joinButton.setOnClickListener(v -> {
            // Add logic to join the voice room
        });

        return convertView;
    }

    private long calculateRemainingTime(String startDateString, String endDateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            long startDateMillis = dateFormat.parse(startDateString).getTime();
            long endDateMillis = dateFormat.parse(endDateString).getTime();
            long currentTimeMillis = System.currentTimeMillis();

            if (currentTimeMillis < startDateMillis) {
                // Room hasn't started yet, count down to start
                return startDateMillis - currentTimeMillis;
            } else if (currentTimeMillis < endDateMillis) {
                // Room is active, count down to end
                return endDateMillis - currentTimeMillis;
            } else {
                // Room has ended
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void startCountdownTimer(long remainingTimeMillis, ProgressBar progressBar, TextView remainTimeText) {
        if (remainingTimeMillis <= 0) {
            progressBar.setProgress(0);
            remainTimeText.setText(getContext().getString(R.string.voice_room_time_remain) + "00:00:00");
            return;
        }

        int totalDurationSeconds = (int) (remainingTimeMillis / 1000);
        progressBar.setMax(totalDurationSeconds);
        progressBar.setProgress(totalDurationSeconds);

        new CountDownTimer(remainingTimeMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                progressBar.setProgress(secondsRemaining); // Update progress
                remainTimeText.setText(getContext().getString(R.string.voice_room_time_remain) +
                        formatRemainingTime(secondsRemaining));
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                remainTimeText.setText(getContext().getString(R.string.voice_room_time_remain) + "00:00:00");
            }
        }.start();
    }

    private String formatRemainingTime(int seconds) {
        int hours = seconds / 3600;          // 3600 seconds = 1 hour
        int minutes = (seconds % 3600) / 60; // Remaining seconds after hours, divided by 60
        int remainingSeconds = seconds % 60;  // Remaining seconds after minutes
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}