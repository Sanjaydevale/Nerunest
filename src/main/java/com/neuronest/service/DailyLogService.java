package com.neuronest.service;

import com.neuronest.dto.DailyLogRequest;
import com.neuronest.dto.DailyLogResponse;
import com.neuronest.model.DailyLog;
import com.neuronest.model.User;
import com.neuronest.repository.DailyLogRepository;
import com.neuronest.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyLogService {

    private final DailyLogRepository dailyLogRepository;
    private final UserRepository userRepository;
    private final ProductivityService productivityService;

    public DailyLogService(DailyLogRepository dailyLogRepository,
                           UserRepository userRepository,
                           ProductivityService productivityService) {
        this.dailyLogRepository = dailyLogRepository;
        this.userRepository = userRepository;
        this.productivityService = productivityService;
    }

    @Transactional
    public DailyLogResponse createLog(DailyLogRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        double score = productivityService.calculateScore(
                request.getStudyHours(), request.getSleepHours(), request.getDistractionHours());

        Optional<DailyLog> previousLog = dailyLogRepository.findTopByUserIdOrderByDateDesc(user.getId());
        if (previousLog.isPresent()) {
            double prevScore = previousLog.get().getProductivityScore();
            if (score > prevScore) {
                int creditsEarned = (int) ((score - prevScore) * 2);
                user.setCredits(user.getCredits() + creditsEarned);
            }
        } else {
            int baseCredits = (int) (score * 2);
            if (baseCredits > 0) {
                user.setCredits(user.getCredits() + baseCredits);
            }
        }

        userRepository.save(user);

        DailyLog log = new DailyLog();
        log.setDate(LocalDate.now());
        log.setStudyHours(request.getStudyHours());
        log.setSleepHours(request.getSleepHours());
        log.setDistractionHours(request.getDistractionHours());
        log.setMood(request.getMood());
        log.setProductivityScore(score);
        log.setUser(user);

        DailyLog saved = dailyLogRepository.save(log);

        return mapToResponse(saved,
                productivityService.detectBurnout(request.getSleepHours(), request.getStudyHours()),
                productivityService.generateSuggestion(request.getDistractionHours(), request.getSleepHours()));
    }

    public List<DailyLogResponse> getLogsByUser(Long userId) {
        return dailyLogRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(log -> mapToResponse(log,
                        productivityService.detectBurnout(log.getSleepHours(), log.getStudyHours()),
                        productivityService.generateSuggestion(log.getDistractionHours(), log.getSleepHours())))
                .collect(Collectors.toList());
    }

    private DailyLogResponse mapToResponse(DailyLog log, String burnout, String suggestion) {
        return DailyLogResponse.builder()
                .id(log.getId())
                .date(log.getDate())
                .studyHours(log.getStudyHours())
                .sleepHours(log.getSleepHours())
                .distractionHours(log.getDistractionHours())
                .mood(log.getMood())
                .productivityScore(log.getProductivityScore())
                .burnoutStatus(burnout)
                .suggestion(suggestion)
                .userId(log.getUser().getId())
                .build();
    }
}