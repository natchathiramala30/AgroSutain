package com.agrosustain.repository;

import com.agrosustain.model.SdgMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SdgMetricRepository extends JpaRepository<SdgMetric, Long> {
    Optional<SdgMetric> findTopByUserIdOrderByCreatedAtDesc(Long userId);
    Optional<SdgMetric> findTopByOrderByCreatedAtDesc();
}
