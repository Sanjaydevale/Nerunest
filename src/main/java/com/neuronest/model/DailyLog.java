package com.neuronest.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_log")
public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int studyHours;

    @Column(nullable = false)
    private int sleepHours;

    @Column(nullable = false)
    private int distractionHours;

    private String mood;

    private double productivityScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public DailyLog() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getStudyHours() { return studyHours; }
    public void setStudyHours(int studyHours) { this.studyHours = studyHours; }

    public int getSleepHours() { return sleepHours; }
    public void setSleepHours(int sleepHours) { this.sleepHours = sleepHours; }

    public int getDistractionHours() { return distractionHours; }
    public void setDistractionHours(int distractionHours) { this.distractionHours = distractionHours; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public double getProductivityScore() { return productivityScore; }
    public void setProductivityScore(double productivityScore) { this.productivityScore = productivityScore; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}