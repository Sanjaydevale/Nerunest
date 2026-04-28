package com.neuronest.dto;

public class DailyLogRequest {
    private Long userId;
    private int studyHours;
    private int sleepHours;
    private int distractionHours;
    private String mood;

    public DailyLogRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getStudyHours() { return studyHours; }
    public void setStudyHours(int studyHours) { this.studyHours = studyHours; }

    public int getSleepHours() { return sleepHours; }
    public void setSleepHours(int sleepHours) { this.sleepHours = sleepHours; }

    public int getDistractionHours() { return distractionHours; }
    public void setDistractionHours(int distractionHours) { this.distractionHours = distractionHours; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }
}
