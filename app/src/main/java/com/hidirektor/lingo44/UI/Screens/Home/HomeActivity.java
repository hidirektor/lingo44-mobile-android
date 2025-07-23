package com.hidirektor.lingo44.UI.Screens.Home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hidirektor.lingo44.R;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private static final int[] HEADER_IMAGES = {
            R.drawable.colosseum,
            R.drawable.eiffel,
            R.drawable.bigben,
            R.drawable.sagrada_familia,
            R.drawable.statue_of_liberty,
            R.drawable.taj_mahal,
            R.drawable.machu_pichu
    };

    private static final int[][] MONUMENT_INFO = {
            {R.string.monument_colosseum_title, R.string.monument_colosseum_location, R.string.monument_colosseum_desc},
            {R.string.monument_eiffel_title, R.string.monument_eiffel_location, R.string.monument_eiffel_desc},
            {R.string.monument_bigben_title, R.string.monument_bigben_location, R.string.monument_bigben_desc},
            {R.string.monument_sagrada_title, R.string.monument_sagrada_location, R.string.monument_sagrada_desc},
            {R.string.monument_statue_of_liberty_title, R.string.monument_statue_of_liberty_location, R.string.monument_statue_of_liberty_desc},
            {R.string.monument_taj_mahal_title, R.string.monument_taj_mahal_location, R.string.monument_taj_mahal_desc},
            {R.string.monument_machu_pichu_title, R.string.monument_machu_pichu_location, R.string.monument_machu_pichu_desc}
    };
    private int currentMonumentIndex = 0;
    private ImageView headerDecoration;
    private Handler popupHandler = new Handler(Looper.getMainLooper());
    private Runnable popupRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView headerSubtitle = findViewById(R.id.headerSubtitle);
        headerSubtitle.setText(getString(R.string.home_header_subtitle));

        headerDecoration = findViewById(R.id.headerDecoration);
        currentMonumentIndex = new Random().nextInt(HEADER_IMAGES.length);
        headerDecoration.setImageResource(HEADER_IMAGES[currentMonumentIndex]);

        headerDecoration.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;
            private int clickCount = 0;

            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                if (now - lastClickTime < 400) {
                    clickCount++;
                } else {
                    clickCount = 1;
                }
                lastClickTime = now;
                if (clickCount == 2) {
                    // Çift tık: popup açılmasın, görsel değişsin
                    if (popupRunnable != null) popupHandler.removeCallbacks(popupRunnable);
                    int newIndex = currentMonumentIndex;
                    while (newIndex == currentMonumentIndex) {
                        newIndex = new Random().nextInt(HEADER_IMAGES.length);
                    }
                    currentMonumentIndex = newIndex;
                    headerDecoration.setImageResource(HEADER_IMAGES[currentMonumentIndex]);
                    clickCount = 0;
                } else if (clickCount == 1) {
                    // Tek tık: gecikmeli popup aç
                    if (popupRunnable != null) popupHandler.removeCallbacks(popupRunnable);
                    popupRunnable = () -> {
                        MonumentInfoBottomSheet.newInstance(
                                MONUMENT_INFO[currentMonumentIndex][0],
                                MONUMENT_INFO[currentMonumentIndex][1],
                                MONUMENT_INFO[currentMonumentIndex][2],
                                HEADER_IMAGES[currentMonumentIndex]
                        ).show(HomeActivity.this.getSupportFragmentManager(), "MonumentInfoBottomSheet");
                        clickCount = 0;
                    };
                    popupHandler.postDelayed(popupRunnable, 300);
                }
            }
        });
    }
} 