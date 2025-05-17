package com.hidirektor.lingo44.UI.Screens.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.hidirektor.lingo44.BaseActivity;
import com.hidirektor.lingo44.R;
import com.hidirektor.lingo44.UI.Screens.Welcome.WelcomeActivity;
import com.hidirektor.lingo44.Utility.Models.Course.Adapter.CourseAdapter;
import com.hidirektor.lingo44.Utility.Models.Course.CourseModel;
import com.hidirektor.lingo44.Utility.Preferences.SPUtil;
import com.hidirektor.lingo44.Utility.Preferences.Theme.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseSelectionActivity extends BaseActivity {

    private ImageView backButton;
    private ImageView themeChangerButton;
    private ListView courseListView;
    private List<CourseModel> courseList;
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_course);

        componentInitialize();

        themeChangerButton.setOnClickListener(v -> {
            ThemeUtil.changeTheme(CourseSelectionActivity.this);
        });
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(CourseSelectionActivity.this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateCourseIcons();

        String selectedCourse = SPUtil.getSelectedCourse(this);
        if (selectedCourse != null) {
            setSelectedCourse(selectedCourse);
        }
    }

    private void componentInitialize() {
        backButton = findViewById(R.id.backButton);
        themeChangerButton = findViewById(R.id.themeChangerButton);
        courseListView = findViewById(R.id.courseListView);

        courseList = new ArrayList<>();
        courseList.add(new CourseModel(
                R.drawable.icon_course_ielts,
                R.drawable.icon_course_ielts,
                this.getString(R.string.course_ielts_general),
                this.getString(R.string.desc_ielts_general)
        ));

        courseList.add(new CourseModel(
                R.drawable.icon_course_ielts,
                R.drawable.icon_course_ielts,
                this.getString(R.string.course_ielts_academic),
                this.getString(R.string.desc_ielts_academic)
        ));

        courseList.add(new CourseModel(
                R.drawable.icon_course_toefl,
                R.drawable.icon_course_toefl,
                this.getString(R.string.course_toefl),
                this.getString(R.string.desc_toefl)
        ));

        courseList.add(new CourseModel(
                R.drawable.icon_course_goethe,
                R.drawable.icon_course_goethe,
                this.getString(R.string.course_goethe),
                this.getString(R.string.desc_goethe)
        ));

        courseList.add(new CourseModel(
                R.drawable.icon_course_goethe,
                R.drawable.icon_course_goethe,
                this.getString(R.string.course_goethe_family),
                this.getString(R.string.desc_goethe_family)
        ));

        courseList.add(new CourseModel(
                R.drawable.icon_course_osym_light,
                R.drawable.icon_course_osym_dark,
                this.getString(R.string.course_yds),
                this.getString(R.string.desc_yds)
        ));

        courseList.add(new CourseModel(
                R.drawable.icon_course_osym_light,
                R.drawable.icon_course_osym_dark,
                this.getString(R.string.course_yks_dil),
                this.getString(R.string.desc_yks_dil)
        ));

        adapter = new CourseAdapter(this, courseList);
        courseListView.setAdapter(adapter);
    }

    private void setSelectedCourse(String selectedCourse) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getName().equals(selectedCourse)) {
                adapter.setSelectedPosition(i);
                courseListView.setSelection(i);
                break;
            }
        }
    }

    private void updateCourseIcons() {
        for (CourseModel course : courseList) {
            courseListView.invalidateViews();
        }
    }
}
