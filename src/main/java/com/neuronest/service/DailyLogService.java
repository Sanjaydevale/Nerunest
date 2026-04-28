package com.neuronest.service;

import com.neuronest.dto.DailyLogRequest;
import com.neuronest.dto.DailyLogResponse;
import com.neuronest.exception.UserNotFoundException;
import com.neuronest.model.DailyLog;
import com.neuronest.model.User;
import com.neuronest.repository.DailyLogRepository;
import com.neuronest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyLogService {

    private final DailyLogRepository dailyLogRepository;
    private final UserRepository userRepository;

    public DailyLogResponse createLog(DailyLogRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        double productivityScore = calculateProductivityScore(
                request.getStudyHours(),
                request.getSleepHours(),
                request.getDistractionHours()
        );

        DailyLog log = DailyLog.builder()
                .date(request.getDate())
                .studyHours(request.getStudyHours())
                .sleepHours(request.getSleepHours())
                .distractionHours(request.getDistractionHours())
                .mood(request.getMood())
                .productivityScore(productivityScore)
                .user(user)
                .build();

        DailyLog saved = dailyLogRepository.save(log);
        return toResponse(saved);
    }

    public List<DailyLogResponse> getLogsByUserId(Long userId) {
        return dailyLogRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private double calculateProductivityScore(double studyHours, double sleepHours, double distractionHours) {
        return (studyHours * 2) + (sleepHours * 1.5) - (distractionHours * 2);
    }

    private DailyLogResponse toResponse(DailyLog log) {
        return DailyLogResponse.builder()
                .id(log.getId())
                .userId(log.getUser().getId())
                .date(log.getDate())
                .studyHours(log.getStudyHours())
                .sleepHours(log.getSleepHours())
                .distractionHours(log.getDistractionHours())
                .mood(log.getMood())
                .productivityScore(log.getProductivityScore())
                .build();
    }
}
