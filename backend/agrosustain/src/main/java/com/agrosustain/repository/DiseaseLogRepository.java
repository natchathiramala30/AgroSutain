package com.agrosustain.repository;

import com.agrosustain.model.DiseaseDetectionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseLogRepository extends JpaRepository<DiseaseDetectionLog, Long> {
    List<DiseaseDetectionLog> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<DiseaseDetectionLog> findTop5ByOrderByCreatedAtDesc();
}
