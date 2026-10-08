package com.agrosustain.repository;

import com.agrosustain.model.IrrigationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IrrigationRepository extends JpaRepository<IrrigationSchedule, Long> {
    List<IrrigationSchedule> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<IrrigationSchedule> findTop5ByOrderByCreatedAtDesc();
}
