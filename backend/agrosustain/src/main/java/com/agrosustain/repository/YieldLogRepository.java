package com.agrosustain.repository;

import com.agrosustain.model.YieldPredictionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YieldLogRepository extends JpaRepository<YieldPredictionLog, Long> {
    List<YieldPredictionLog> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<YieldPredictionLog> findTop5ByOrderByCreatedAtDesc();
}
