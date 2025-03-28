package com.hidirektor.lingify.Utility.Models.VoiceRoom;

public class VoiceRoom {
    String roomSubject;
    String adminName;
    String adminImageUrl;
    String startDate;
    String endDate;
    int participantCount;
    int maxParticipants;
    boolean isActive;
    String topicImageUrl;

    public VoiceRoom(String roomSubject, String adminName, String adminImageUrl,
                     String startDate, String endDate, int participantCount,
                     int maxParticipants, boolean isActive, String topicImageUrl) {
        this.roomSubject = roomSubject;
        this.adminName = adminName;
        this.adminImageUrl = adminImageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participantCount = participantCount;
        this.maxParticipants = maxParticipants;
        this.isActive = isActive;
        this.topicImageUrl = topicImageUrl;
    }

    public String getRoomSubject() {
        return roomSubject;
    }

    public void setRoomSubject(String roomSubject) {
        this.roomSubject = roomSubject;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminImageUrl() {
        return adminImageUrl;
    }

    public void setAdminImageUrl(String adminImageUrl) {
        this.adminImageUrl = adminImageUrl;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getTopicImageUrl() {
        return topicImageUrl;
    }

    public void setTopicImageUrl(String topicImageUrl) {
        this.topicImageUrl = topicImageUrl;
    }
}