package com.neuronest.controller;

import com.neuronest.dto.DailyLogRequest;
import com.neuronest.dto.DailyLogResponse;
import com.neuronest.service.DailyLogService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class DailyLogController {

    private final DailyLogService dailyLogService;

    @PostMapping
    public ResponseEntity<DailyLogResponse> createLog(@Valid @RequestBody DailyLogRequest request) {
        DailyLogResponse response = dailyLogService.createLog(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<DailyLogResponse>> getLogsByUserId(@PathVariable Long userId) {
        List<DailyLogResponse> logs = dailyLogService.getLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }
}
