package com.agrosustain.service;

import com.agrosustain.dto.YieldRequest;
import com.agrosustain.dto.YieldResponse;
import com.agrosustain.model.User;
import com.agrosustain.model.YieldPredictionLog;
import com.agrosustain.repository.UserRepository;
import com.agrosustain.repository.YieldLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YieldService {

    private final AiClientService aiClientService;
    private final YieldLogRepository yieldLogRepository;
    private final UserRepository userRepository;

    public YieldService(AiClientService aiClientService, YieldLogRepository yieldLogRepository, UserRepository userRepository) {
        this.aiClientService = aiClientService;
        this.yieldLogRepository = yieldLogRepository;
        this.userRepository = userRepository;
    }

    public YieldResponse predictYield(YieldRequest request) {
        YieldResponse response = aiClientService.predictYield(request);

        try {
            YieldPredictionLog log = new YieldPredictionLog();
            if (request.getUserId() != null) {
                User user = userRepository.findById(request.getUserId()).orElse(null);
                log.setUser(user);
            }
            log.setCropName(response.getCropName());
            log.setAreaAcres(response.getAreaAcres());
            log.setSoilType(request.getSoilType());
            log.setFertilizerUsageKg(request.getFertilizerUsageKg());
            log.setRainfallMm(request.getRainfallMm());
            log.setPredictedYieldTons(response.getTotalPredictedYieldTons());
            log.setPredictedYieldPerAcre(response.getPredictedYieldPerAcreTons());
            log.setEstimatedRevenueUsd(response.getEstimatedGrossRevenueUsd());
            log.setSdg2FoodProductionImpact(response.getSdg2FoodSecurityImpact());

            yieldLogRepository.save(log);
        } catch (Exception e) {
            System.err.println("Note: Could not save yield log to DB: " + e.getMessage());
        }

        return response;
    }

    public List<YieldPredictionLog> getHistory(Long userId) {
        if (userId != null) {
            return yieldLogRepository.findByUserIdOrderByCreatedAtDesc(userId);
        }
        return yieldLogRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
