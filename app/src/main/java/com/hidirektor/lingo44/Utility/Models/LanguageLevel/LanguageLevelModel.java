package com.hidirektor.lingo44.Utility.Models.LanguageLevel;

public class LanguageLevelModel {
    private String levelName;
    private String language;

    public LanguageLevelModel(String levelName, String language) {
        this.levelName = levelName;
        this.language = language;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return levelName;
    }
}