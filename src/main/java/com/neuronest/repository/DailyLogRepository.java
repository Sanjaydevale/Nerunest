package com.neuronest.repository;

import com.neuronest.model.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {

    List<DailyLog> findByUserId(Long userId);
}
