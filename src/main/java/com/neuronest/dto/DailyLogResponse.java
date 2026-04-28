package com.neuronest.dto;

import java.time.LocalDate;

public class DailyLogResponse {
    private Long id;
    private LocalDate date;
    private int studyHours;
    private int sleepHours;
    private int distractionHours;
    private String mood;
    private double productivityScore;
    private String burnoutStatus;
    private String suggestion;
    private Long userId;

    public DailyLogResponse() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final DailyLogResponse obj = new DailyLogResponse();

        public Builder id(Long id) { obj.id = id; return this; }
        public Builder date(LocalDate date) { obj.date = date; return this; }
        public Builder studyHours(int v) { obj.studyHours = v; return this; }
        public Builder sleepHours(int v) { obj.sleepHours = v; return this; }
        public Builder distractionHours(int v) { obj.distractionHours = v; return this; }
        public Builder mood(String v) { obj.mood = v; return this; }
        public Builder productivityScore(double v) { obj.productivityScore = v; return this; }
        public Builder burnoutStatus(String v) { obj.burnoutStatus = v; return this; }
        public Builder suggestion(String v) { obj.suggestion = v; return this; }
        public Builder userId(Long v) { obj.userId = v; return this; }
        public DailyLogResponse build() { return obj; }
    }

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public int getStudyHours() { return studyHours; }
    public int getSleepHours() { return sleepHours; }
    public int getDistractionHours() { return distractionHours; }
    public String getMood() { return mood; }
    public double getProductivityScore() { return productivityScore; }
    public String getBurnoutStatus() { return burnoutStatus; }
    public String getSuggestion() { return suggestion; }
    public Long getUserId() { return userId; }
}