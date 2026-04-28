package com.neuronest.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyLogResponse {

    private Long id;
    private Long userId;
    private LocalDate date;
    private double studyHours;
    private double sleepHours;
    private double distractionHours;
    private String mood;
    private double productivityScore;
}
