package com.neuronest.service;

import org.springframework.stereotype.Service;

@Service
public class ProductivityService {

    public double calculateScore(int studyHours, int sleepHours, int distractionHours) {
        return (studyHours * 2.0) + (sleepHours * 1.5) - (distractionHours * 2.0);
    }

    public String detectBurnout(int sleepHours, int studyHours) {
        if (sleepHours < 5 && studyHours > 8) {
            return "High Burnout Risk";
        }
        return "Normal";
    }

    public String generateSuggestion(int distractionHours, int sleepHours) {
        if (distractionHours > 3 && sleepHours < 6) {
            return "Reduce distractions and improve your sleep schedule.";
        } else if (distractionHours > 3) {
            return "Reduce distractions to boost your productivity.";
        } else if (sleepHours < 6) {
            return "Improve your sleep schedule for better performance.";
        }
        return "Keep up the great work! You're on track.";
    }
}