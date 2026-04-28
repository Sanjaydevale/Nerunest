package com.neuronest;

import com.neuronest.service.DailyLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.neuronest.dto.DailyLogRequest;
import com.neuronest.dto.DailyLogResponse;
import com.neuronest.model.DailyLog;
import com.neuronest.model.User;
import com.neuronest.repository.DailyLogRepository;
import com.neuronest.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DailyLogServiceTest {

    @Mock
    private DailyLogRepository dailyLogRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DailyLogService dailyLogService;

    @Test
    void createLog_calculatesProductivityScore() {
        User user = User.builder().id(1L).name("Alice").email("alice@example.com").password("secret").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        DailyLog saved = DailyLog.builder()
                .id(10L)
                .date(LocalDate.of(2024, 1, 1))
                .studyHours(6)
                .sleepHours(8)
                .distractionHours(2)
                .mood("focused")
                .productivityScore((6 * 2) + (8 * 1.5) - (2 * 2))
                .user(user)
                .build();
        when(dailyLogRepository.save(any(DailyLog.class))).thenReturn(saved);

        DailyLogRequest request = DailyLogRequest.builder()
                .userId(1L)
                .date(LocalDate.of(2024, 1, 1))
                .studyHours(6)
                .sleepHours(8)
                .distractionHours(2)
                .mood("focused")
                .build();

        DailyLogResponse response = dailyLogService.createLog(request);

        // score = (6*2) + (8*1.5) - (2*2) = 12 + 12 - 4 = 20
        assertThat(response.getProductivityScore()).isEqualTo(20.0);
        assertThat(response.getUserId()).isEqualTo(1L);
    }

    @Test
    void getLogsByUserId_returnsMappedResponses() {
        User user = User.builder().id(2L).name("Bob").email("bob@example.com").password("pass").build();
        DailyLog log = DailyLog.builder()
                .id(5L)
                .date(LocalDate.of(2024, 2, 1))
                .studyHours(4)
                .sleepHours(7)
                .distractionHours(1)
                .mood("tired")
                .productivityScore((4 * 2) + (7 * 1.5) - (1 * 2))
                .user(user)
                .build();
        when(dailyLogRepository.findByUserId(2L)).thenReturn(List.of(log));

        List<DailyLogResponse> responses = dailyLogService.getLogsByUserId(2L);

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getMood()).isEqualTo("tired");
        // score = 8 + 10.5 - 2 = 16.5
        assertThat(responses.get(0).getProductivityScore()).isEqualTo(16.5);
    }
}
