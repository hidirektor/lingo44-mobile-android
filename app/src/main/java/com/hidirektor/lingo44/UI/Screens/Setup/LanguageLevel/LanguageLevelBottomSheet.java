package com.hidirektor.lingo44.UI.Screens.Setup.LanguageLevel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.Utility.Models.LanguageLevel.Adapter.LanguageLevelAdapter;
import com.hidirektor.lingo44.Utility.Models.LanguageLevel.LanguageLevelModel;

import java.util.LinkedList;

public class LanguageLevelBottomSheet extends BottomSheetDialogFragment {

    private LinkedList<LanguageLevelModel> languageLevels;

    public static LanguageLevelBottomSheet newInstance(LinkedList<LanguageLevelModel> languageLevels) {
        LanguageLevelBottomSheet dialog = new LanguageLevelBottomSheet();
        Bundle args = new Bundle();
        args.putSerializable("languageLevels", languageLevels);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialog);
    }

    @Override
    public void onStart() {
        super.onStart();

        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

                behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                            behavior.setShouldRemoveExpandedCorners(true);
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        // Optional: Adjust UI based on slide offset
                    }
                });
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_level_selection, container, false);

        ListView languageLevelListView = view.findViewById(R.id.languageLevelListView);
        Button continueButton = view.findViewById(R.id.continueButton);
        ImageView closeButton = view.findViewById(R.id.closeButton);

        if (getArguments() != null) {
            languageLevels = (LinkedList<LanguageLevelModel>) getArguments().getSerializable("languageLevels");
        }

        LanguageLevelAdapter adapter = new LanguageLevelAdapter(getContext(), languageLevels);
        languageLevelListView.setAdapter(adapter);

        continueButton.setOnClickListener(v -> {
            int selectedPosition = languageLevelListView.getCheckedItemPosition();
            if (selectedPosition != ListView.INVALID_POSITION) {
                LanguageLevelModel selectedLevel = (LanguageLevelModel) languageLevelListView.getItemAtPosition(selectedPosition);
                Log.d("LanguageLevel", "Selected Language Level: " + selectedLevel.getLevelName());
                dismiss();
            }
        });

        closeButton.setOnClickListener(v -> dismiss());

        return view;
    }
}