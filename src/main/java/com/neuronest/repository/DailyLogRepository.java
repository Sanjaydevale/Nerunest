package com.neuronest.repository;

import com.neuronest.model.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {
    List<DailyLog> findByUserIdOrderByDateDesc(Long userId);
    Optional<DailyLog> findTopByUserIdOrderByDateDesc(Long userId);
}
