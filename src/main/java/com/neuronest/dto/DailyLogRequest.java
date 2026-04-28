package com.neuronest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyLogRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "date is required")
    @PastOrPresent(message = "date must not be in the future")
    private LocalDate date;

    @PositiveOrZero(message = "studyHours must be zero or positive")
    private double studyHours;

    @PositiveOrZero(message = "sleepHours must be zero or positive")
    private double sleepHours;

    @PositiveOrZero(message = "distractionHours must be zero or positive")
    private double distractionHours;

    @NotBlank(message = "mood is required")
    private String mood;
}
