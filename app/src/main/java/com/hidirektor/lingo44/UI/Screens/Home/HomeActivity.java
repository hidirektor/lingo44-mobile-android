package com.hidirektor.lingo44.UI.Screens.Home;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.hidirektor.lingo44.BaseActivity;
import com.hidirektor.lingo44.R;

import java.util.Random;

public class HomeActivity extends BaseActivity {
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

        // Sosyal medya kutuları için tıklama işlemleri (yeni sıra ve kutular)
        LinearLayout socialMediaRow = (LinearLayout) findViewById(R.id.socialMediaRow);
        LinearLayout speakingCafeBox = (LinearLayout) socialMediaRow.getChildAt(0);
        LinearLayout kahveDengiBox = (LinearLayout) socialMediaRow.getChildAt(1);
        LinearLayout culturalExchangeBox = (LinearLayout) socialMediaRow.getChildAt(2);

        if (speakingCafeBox != null) {
            speakingCafeBox.setOnClickListener(v -> showSocialMediaPopup(
                    "Speaking Cafe",
                    "https://www.instagram.com/kutayspeakingcafe/",
                    "https://www.google.com/maps/place/Kahve+Dengi+by+Kutay/@37.8609034,27.2591352,17z/data=!3m1!4b1!4m6!3m5!1s0x14bea9b4d104aed3:0xb74005e528363292!8m2!3d37.8609034!4d27.2617101!16s%2Fg%2F11y4ygschf?entry=ttu&g_ep=EgoyMDI1MDcyMC4wIKXMDSoASAFQAw%3D%3D",
                    "https://kutaydil.com/src/speaking-cafe.html"
            ));
        }
        if (kahveDengiBox != null) {
            kahveDengiBox.setOnClickListener(v -> showSocialMediaPopup(
                    "Kahve Dengi by Kutay",
                    "https://www.instagram.com/kahvedengibykutay/",
                    "https://www.google.com/maps/place/Kahve+Dengi+by+Kutay/@37.8609034,27.2591352,17z/data=!3m1!4b1!4m6!3m5!1s0x14bea9b4d104aed3:0xb74005e528363292!8m2!3d37.8609034!4d27.2617101!16s%2Fg%2F11y4ygschf?entry=ttu&g_ep=EgoyMDI1MDcyMC4wIKXMDSoASAFQAw%3D%3D",
                    null
            ));
        }
        if (culturalExchangeBox != null) {
            culturalExchangeBox.setOnClickListener(v -> showSocialMediaPopup(
                    "Kültür Değişim Programı",
                    "https://www.instagram.com/kutayspeakingcafe/",
                    null,
                    "https://kutaydil.com/src/cultural-exchange.html"
            ));
        }

        // Kutay kutusu için popup
        LinearLayout kutayMediaRow = (LinearLayout) findViewById(R.id.kutayMediaRow);
        if (kutayMediaRow != null && kutayMediaRow.getChildCount() > 0) {
            LinearLayout kutayBox = (LinearLayout) kutayMediaRow.getChildAt(0);
            if (kutayBox != null) {
                kutayBox.setOnClickListener(v -> showSocialMediaPopup(
                        "Kutay Yabancı Dil Okulu",
                        "https://www.instagram.com/kutay_yabancidil_okulu/",
                        "https://www.google.com/maps/place/Özel+Kutay+Yabancı+Dil+ve+Kişisel+Gelişim+Kursu/@37.8627651,27.2639631,17z/data=!3m1!4b1!4m6!3m5!1s0x14bea9333b804e47:0xa90f697944922c07!8m2!3d37.8627651!4d27.266538!16s%2Fg%2F11j4t6h4__?entry=ttu&g_ep=EgoyMDI1MDcyMC4wIKXMDSoASAFQAw%3D%3D",
                        "https://kutaydil.com"
                ));
            }
        }

        // Mystery oyun kutusu için popup
        LinearLayout gameLogoRow = (LinearLayout) findViewById(R.id.gameLogoRow);
        if (gameLogoRow != null && gameLogoRow.getChildCount() > 3) {
            LinearLayout mysteryBox = (LinearLayout) gameLogoRow.getChildAt(3);
            if (mysteryBox != null) {
                mysteryBox.setOnClickListener(v -> showMysteryGameDialog());
            }
        }

        // Bottom bar buttons
        ConstraintLayout homeButton = findViewById(R.id.homeButton);
        ConstraintLayout learnButton = findViewById(R.id.learnButton);
        ConstraintLayout profileButton = findViewById(R.id.profileButton);

        View[] bottomButtons = {homeButton, learnButton, profileButton};
        for (View btn : bottomButtons) {
            if (btn != null) {
                // Add ripple effect if not already present
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int rippleColor = getResources().getColor(R.color.body_secondary, null);
                    RippleDrawable rippleDrawable = new RippleDrawable(
                            ColorStateList.valueOf(rippleColor),
                            btn.getBackground(),
                            null
                    );
                    btn.setBackground(rippleDrawable);
                }
                btn.setClickable(true);
                btn.setFocusable(true);
            }
        }

        if (homeButton != null) {
            homeButton.setOnClickListener(v ->
                    Toast.makeText(this, getString(R.string.bottom_nav_home), Toast.LENGTH_SHORT).show()
            );
        }
        if (learnButton != null) {
            learnButton.setOnClickListener(v ->
                    Toast.makeText(this, getString(R.string.bottom_nav_learn), Toast.LENGTH_SHORT).show()
            );
        }
        if (profileButton != null) {
            profileButton.setOnClickListener(v ->
                    Toast.makeText(this, getString(R.string.bottom_nav_profile), Toast.LENGTH_SHORT).show()
            );
        }
    }

    private void showSocialMediaPopup(String title, String instagramUrl, String mapsUrl, String websiteUrl) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_social_media);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        TextView titleView = dialog.findViewById(R.id.socialMediaTitle);
        titleView.setText(title);
        LinearLayout instagramRow = dialog.findViewById(R.id.instagramRow);
        LinearLayout mapsRow = dialog.findViewById(R.id.mapsRow);
        LinearLayout websiteRow = dialog.findViewById(R.id.websiteRow);
        TextView instagramLink = dialog.findViewById(R.id.instagramLink);
        TextView mapsLink = dialog.findViewById(R.id.mapsLink);
        TextView websiteLink = dialog.findViewById(R.id.websiteLink);
        ImageView closeButton = dialog.findViewById(R.id.closeButton);

        instagramRow.setOnClickListener(v -> openUrl(instagramUrl));
        instagramLink.setOnClickListener(v -> openUrl(instagramUrl));
        mapsRow.setOnClickListener(v -> openUrl(mapsUrl));
        mapsLink.setOnClickListener(v -> openUrl(mapsUrl));
        if (websiteUrl != null) {
            websiteRow.setVisibility(View.VISIBLE);
            websiteRow.setOnClickListener(v -> openUrl(websiteUrl));
            websiteLink.setOnClickListener(v -> openUrl(websiteUrl));
        } else {
            websiteRow.setVisibility(View.GONE);
        }
        closeButton.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void openUrl(String url) {
        if (url == null) return;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void showMysteryGameDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.mystery_game_title)
                .setMessage(R.string.mystery_game_coming_soon)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
} 