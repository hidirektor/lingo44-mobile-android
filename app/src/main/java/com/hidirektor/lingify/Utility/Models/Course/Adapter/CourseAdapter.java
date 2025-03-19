package com.hidirektor.lingify.Utility.Models.Course.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.UI.Screens.Setup.LanguageLevel.DetectSelectionActivity;
import com.hidirektor.lingify.Utility.Models.Course.CourseModel;
import com.hidirektor.lingify.Utility.Preferences.SPUtil;

import java.util.List;

public class CourseAdapter extends BaseAdapter {
    private Context context;
    private List<CourseModel> courseList;
    private int selectedPosition = -1;

    public CourseAdapter(Context context, List<CourseModel> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_course, parent, false);
            holder = new ViewHolder();
            holder.mainItemLayout = convertView.findViewById(R.id.mainItemLayout);
            holder.icon = convertView.findViewById(R.id.courseIcon);
            holder.name = convertView.findViewById(R.id.courseName);
            holder.infoDescription = convertView.findViewById(R.id.courseInfo);
            holder.selectIcon = convertView.findViewById(R.id.selectIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CourseModel course = courseList.get(position);
        holder.icon.setImageResource(course.getIconForCurrentTheme());
        holder.name.setText(course.getName());
        holder.infoDescription.setText(course.getInfoDescription());

        if (position == selectedPosition) {
            holder.mainItemLayout.setBackgroundResource(R.drawable.background_item_course_active);
        } else {
            holder.mainItemLayout.setBackgroundResource(R.drawable.background_item_course_not_active);
        }

        holder.mainItemLayout.setOnClickListener(v -> {
            if (selectedPosition == position) {
                selectedPosition = -1;
            } else {
                selectedPosition = position;
            }

            String selectedCourse = course.getName();

            SPUtil.saveUserSetup(context, selectedCourse);

            notifyDataSetChanged();
        });

        holder.selectIcon.setOnClickListener(v -> {
            if (selectedPosition == position) {
                selectedPosition = -1;
            } else {
                selectedPosition = position;
            }

            String selectedCourse = course.getName();
            SPUtil.saveUserSetup(context, selectedCourse);

            notifyDataSetChanged();

            v.postDelayed(() -> {
                Intent intent = new Intent(context, DetectSelectionActivity.class);
                context.startActivity(intent);
            }, 100);
        });

        return convertView;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        LinearLayout mainItemLayout;
        ImageView icon;
        TextView name;
        TextView infoDescription;
        ImageView selectIcon;
    }
}