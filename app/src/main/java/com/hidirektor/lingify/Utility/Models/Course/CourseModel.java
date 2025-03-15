package com.hidirektor.lingify.Utility.Models.Course;

import androidx.appcompat.app.AppCompatDelegate;

public class CourseModel {
    private int lightIcon;
    private int darkIcon;
    private String name;
    private String infoDescription;

    // Constructor
    public CourseModel(int lightIcon, int darkIcon, String name, String infoDescription) {
        this.lightIcon = lightIcon;
        this.darkIcon = darkIcon;
        this.name = name;
        this.infoDescription = infoDescription;
    }

    // Getter methods
    public int getIconForCurrentTheme() {
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        return (currentMode == AppCompatDelegate.MODE_NIGHT_YES) ? darkIcon : lightIcon;
    }

    // Getter for name and description
    public String getName() {
        return name;
    }

    public String getInfoDescription() {
        return infoDescription;
    }
}