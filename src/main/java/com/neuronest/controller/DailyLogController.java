package com.neuronest.controller;

import com.neuronest.dto.DailyLogRequest;
import com.neuronest.dto.DailyLogResponse;
import com.neuronest.service.DailyLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*")
public class DailyLogController {

    private final DailyLogService dailyLogService;

    public DailyLogController(DailyLogService dailyLogService) {
        this.dailyLogService = dailyLogService;
    }

    @PostMapping
    public ResponseEntity<DailyLogResponse> createLog(@RequestBody DailyLogRequest request) {
        return ResponseEntity.ok(dailyLogService.createLog(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<DailyLogResponse>> getLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(dailyLogService.getLogsByUser(userId));
    }
}