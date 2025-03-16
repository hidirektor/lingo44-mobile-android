package com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel;

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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.LanguageLevel.Adapter.LanguageLevelAdapter;
import com.hidirektor.lingify.Utility.Models.LanguageLevel.LanguageLevelModel;

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