package com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel.Exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.LevelExam.Basic.ExamModel;

import java.util.LinkedList;

public class LevelExamBottomSheet extends BottomSheetDialogFragment {

    private LinkedList<ExamModel> examModels;

    public static LevelExamBottomSheet newInstance(LinkedList<ExamModel> examModels) {
        LevelExamBottomSheet dialog = new LevelExamBottomSheet();
        Bundle args = new Bundle();
        args.putSerializable("examModels", examModels);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialog);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_level_selection, container, false);



        if (getArguments() != null) {
            examModels = (LinkedList<ExamModel>) getArguments().getSerializable("examModels");
        }

        return view;
    }
}
