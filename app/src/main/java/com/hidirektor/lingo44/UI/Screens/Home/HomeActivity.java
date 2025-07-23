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
            R.drawable.machu_pichu,
            R.drawable.petra,
            R.drawable.great_wall,
            R.drawable.ayasofya,
            R.drawable.stonehenge,
            R.drawable.angkor_wat,
            R.drawable.alhambra,
            R.drawable.brandenburg,
            R.drawable.sydney_opera_house,
            R.drawable.christ_redeemer,
            R.drawable.pisa_tower,
            R.drawable.acropolis,
            R.drawable.kremlin,
            R.drawable.galata_tower
    };

    private static final int[][] MONUMENT_INFO = {
            {R.string.monument_colosseum_title, R.string.monument_colosseum_location, R.string.monument_colosseum_desc},
            {R.string.monument_eiffel_title, R.string.monument_eiffel_location, R.string.monument_eiffel_desc},
            {R.string.monument_bigben_title, R.string.monument_bigben_location, R.string.monument_bigben_desc},
            {R.string.monument_sagrada_title, R.string.monument_sagrada_location, R.string.monument_sagrada_desc},
            {R.string.monument_statue_of_liberty_title, R.string.monument_statue_of_liberty_location, R.string.monument_statue_of_liberty_desc},
            {R.string.monument_taj_mahal_title, R.string.monument_taj_mahal_location, R.string.monument_taj_mahal_desc},
            {R.string.monument_machu_pichu_title, R.string.monument_machu_pichu_location, R.string.monument_machu_pichu_desc},
            {R.string.monument_petra_title, R.string.monument_petra_location, R.string.monument_petra_desc},
            {R.string.monument_great_wall_title, R.string.monument_great_wall_location, R.string.monument_great_wall_desc},
            {R.string.monument_ayasofya_title, R.string.monument_ayasofya_location, R.string.monument_ayasofya_desc}, // Ayasofya
            {R.string.monument_stonehenge_title, R.string.monument_stonehenge_location, R.string.monument_stonehenge_desc}, // Stonehenge
            {R.string.monument_angkorwat_title, R.string.monument_angkorwat_location, R.string.monument_angkorwat_desc}, // Angkor Wat
            {R.string.monument_alhambra_title, R.string.monument_alhambra_location, R.string.monument_alhambra_desc}, // Alhambra
            {R.string.monument_brandenburg_title, R.string.monument_brandenburg_location, R.string.monument_brandenburg_desc}, // Brandenburg Kapısı
            {R.string.monument_sydney_opera_title, R.string.monument_sydney_opera_location, R.string.monument_sydney_opera_desc},
            {R.string.monument_christ_redeemer_title, R.string.monument_christ_redeemer_location, R.string.monument_christ_redeemer_desc},
            {R.string.monument_pisa_tower_title, R.string.monument_pisa_tower_location, R.string.monument_pisa_tower_desc},
            {R.string.monument_acropolis_title, R.string.monument_acropolis_location, R.string.monument_acropolis_desc},
            {R.string.monument_kremlin_title, R.string.monument_kremlin_location, R.string.monument_kremlin_desc},
            {R.string.monument_galata_tower_title, R.string.monument_galata_tower_location, R.string.monument_galata_tower_desc}
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