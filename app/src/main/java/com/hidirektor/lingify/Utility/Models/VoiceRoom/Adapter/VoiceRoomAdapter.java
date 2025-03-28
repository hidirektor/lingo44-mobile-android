package com.hidirektor.lingify.Utility.Models.VoiceRoom.Adapter;

import android.content.Context;
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
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.VoiceRoom.VoiceRoom;

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

        ImageView adminImage = convertView.findViewById(R.id.adminImage);
        TextView roomSubject = convertView.findViewById(R.id.roomSubject);
        TextView participantsCount = convertView.findViewById(R.id.participantsCount);
        Button joinButton = convertView.findViewById(R.id.joinButton);
        ProgressBar timeRemainingBar = convertView.findViewById(R.id.timeRemainingBar);
        TextView startDate = convertView.findViewById(R.id.startDate);
        TextView endDate = convertView.findViewById(R.id.endDate);
        TextView remainTime = convertView.findViewById(R.id.remainTime);
        ImageView topicImage = convertView.findViewById(R.id.topicImage);

        if (room != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
            startDate.setText(getContext().getString(R.string.voice_room_start_date) + room.getStartDate());
            endDate.setText(getContext().getString(R.string.voice_room_end_date) + room.getEndDate());

            long remainingTime = calculateRemainingTime(room.getStartDate());
            timeRemainingBar.setMax((int) remainingTime);
            startCountdownTimer(remainingTime, timeRemainingBar, remainTime);

            roomSubject.setText(room.getRoomSubject());
            participantsCount.setText(getContext().getString(R.string.voice_room_student) + ": " + room.getParticipantCount() + "/" + room.getMaxParticipants());

            Glide.with(getContext()).load(room.getAdminImageUrl()).into(adminImage);
            Glide.with(getContext()).load(room.getTopicImageUrl()).into(topicImage);

            joinButton.setOnClickListener(v -> {
                // Handle join button click (e.g., open chat room)
            });
        }

        return convertView;
    }

    private long calculateRemainingTime(String startDateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            long startDateMillis = dateFormat.parse(startDateString).getTime();
            return startDateMillis - System.currentTimeMillis(); // Time left in milliseconds
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void startCountdownTimer(long remainingTime, ProgressBar progressBar, TextView remainTime) {
        new CountDownTimer(remainingTime, 1000) { // 1000ms (1 second) interval
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) (millisUntilFinished / 1000); // seconds left
                progressBar.setProgress(progress);
                remainTime.setText(getContext().getString(R.string.voice_room_time_remain) + formatRemainingTime(progress));
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                remainTime.setText(getContext().getString(R.string.voice_room_time_remain) + "00:00");
            }
        }.start();
    }

    private String formatRemainingTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}