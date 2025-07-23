package com.hidirektor.lingo44.UI.Screens.Home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hidirektor.lingo44.R;

public class MonumentInfoBottomSheet extends BottomSheetDialogFragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_LOCATION = "location";
    private static final String ARG_DESC = "desc";
    private static final String ARG_IMAGE = "image";

    public static MonumentInfoBottomSheet newInstance(int titleRes, int locationRes, int descRes, int imageRes) {
        MonumentInfoBottomSheet fragment = new MonumentInfoBottomSheet();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, titleRes);
        args.putInt(ARG_LOCATION, locationRes);
        args.putInt(ARG_DESC, descRes);
        args.putInt(ARG_IMAGE, imageRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_monument_info, container, false);
        Bundle args = getArguments();
        if (args != null) {
            ((TextView) view.findViewById(R.id.monumentTitle)).setText(getString(args.getInt(ARG_TITLE)));
            ((TextView) view.findViewById(R.id.monumentLocation)).setText(getString(args.getInt(ARG_LOCATION)));
            ((TextView) view.findViewById(R.id.monumentDesc)).setText(getString(args.getInt(ARG_DESC)));
            ((ImageView) view.findViewById(R.id.monumentImage)).setImageResource(args.getInt(ARG_IMAGE));
        }
        view.findViewById(R.id.closeButton).setOnClickListener(v -> dismiss());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.CustomBottomSheetDialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        // Removed custom background, rely on layout background only
        return dialog;
    }
} 