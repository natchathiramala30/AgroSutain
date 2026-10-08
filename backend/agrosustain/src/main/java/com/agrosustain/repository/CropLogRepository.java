package com.agrosustain.repository;

import com.agrosustain.model.CropRecommendationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropLogRepository extends JpaRepository<CropRecommendationLog, Long> {
    List<CropRecommendationLog> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<CropRecommendationLog> findTop5ByOrderByCreatedAtDesc();
}
