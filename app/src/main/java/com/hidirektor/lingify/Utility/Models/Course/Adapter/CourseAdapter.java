package com.hidirektor.lingify.Utility.Models.Course.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hidirektor.lingify.R;
import com.hidirektor.lingify.Utility.Models.Course.CourseModel;

import java.util.List;

public class CourseAdapter extends BaseAdapter {
    private Context context;
    private List<CourseModel> courseList;
    private int selectedPosition = -1;  // Başlangıçta hiçbir item seçili değil

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
        holder.icon.setImageResource(course.getIconForCurrentTheme());  // Ikonları güncelle
        holder.name.setText(course.getName());
        holder.infoDescription.setText(course.getInfoDescription());

        // Item seçildiyse arka planı güncelle
        if (position == selectedPosition) {
            holder.mainItemLayout.setBackgroundResource(R.drawable.background_item_course_active);
        } else {
            holder.mainItemLayout.setBackgroundResource(R.drawable.background_item_course_not_active);
        }

        // Item tıklanma olayı
        holder.mainItemLayout.setOnClickListener(v -> {
            if (selectedPosition == position) {
                // Eğer aynı item seçiliyse, seçimi kaldır
                selectedPosition = -1;
            } else {
                // Yeni bir item seçildi
                selectedPosition = position;
            }
            notifyDataSetChanged();  // Listeyi güncelle
        });

        return convertView;
    }

    static class ViewHolder {
        LinearLayout mainItemLayout;
        ImageView icon;
        TextView name;
        TextView infoDescription;
        ImageView selectIcon;
    }
}