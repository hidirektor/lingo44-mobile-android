package com.hidirektor.lingo44.Utility.Models.LanguageLevel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.Utility.Models.LanguageLevel.LanguageLevelModel;

import java.util.List;

import io.shubh.superiortoastlibrary.SuperiorToast;

public class LanguageLevelAdapter extends BaseAdapter {
    private Context context;
    private List<LanguageLevelModel> levels;

    private int selectedPosition = -1;

    public LanguageLevelAdapter(Context context, List<LanguageLevelModel> levels) {
        this.context = context;
        this.levels = levels;
    }

    @Override
    public int getCount() {
        return levels.size();
    }

    @Override
    public Object getItem(int position) {
        return levels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_level_selection, parent, false);

            holder = new ViewHolder();
            holder.mainItemLayout = convertView.findViewById(R.id.mainItemLayout);
            holder.levelNameTextView = convertView.findViewById(R.id.levelName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LanguageLevelModel level = levels.get(position);
        holder.levelNameTextView.setText(level.getLevelName());

        if (position == selectedPosition) {
            holder.mainItemLayout.setBackgroundResource(R.drawable.background_item_course_active);
        } else {
            holder.mainItemLayout.setBackgroundResource(R.drawable.background_item_course_not_active);
        }

        holder.mainItemLayout.setOnClickListener(v -> {
            selectedPosition = (selectedPosition == position) ? -1 : position;
            notifyDataSetChanged();

            if (level.getLevelName().contains("C2")) {
                SuperiorToast.makeSuperiorToast(context.getApplicationContext(),context.getString(R.string.detect_level_easter_message))
                        .setToastIcon(ResourcesCompat.getDrawable(context.getResources(), R.drawable.icon_easter_message, null))
                        .show();

                selectedPosition = -1;
            }
        });

        return convertView;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        LinearLayout mainItemLayout;
        TextView levelNameTextView;
    }
}