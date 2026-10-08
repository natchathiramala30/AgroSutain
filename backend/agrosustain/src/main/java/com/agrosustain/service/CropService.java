package com.agrosustain.service;

import com.agrosustain.dto.CropRequest;
import com.agrosustain.dto.CropResponse;
import com.agrosustain.model.CropRecommendationLog;
import com.agrosustain.model.User;
import com.agrosustain.repository.CropLogRepository;
import com.agrosustain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    private final AiClientService aiClientService;
    private final CropLogRepository cropLogRepository;
    private final UserRepository userRepository;

    public CropService(AiClientService aiClientService, CropLogRepository cropLogRepository, UserRepository userRepository) {
        this.aiClientService = aiClientService;
        this.cropLogRepository = cropLogRepository;
        this.userRepository = userRepository;
    }

    public CropResponse getRecommendation(CropRequest request) {
        CropResponse response = aiClientService.getCropRecommendation(request);

        // Persist recommendation log
        try {
            CropRecommendationLog log = new CropRecommendationLog();
            if (request.getUserId() != null) {
                User user = userRepository.findById(request.getUserId()).orElse(null);
                log.setUser(user);
            }
            log.setNitrogen(request.getNitrogen());
            log.setPhosphorus(request.getPhosphorus());
            log.setPotassium(request.getPotassium());
            log.setTemperature(request.getTemperature());
            log.setHumidity(request.getHumidity());
            log.setSoilPh(request.getSoilPh());
            log.setRainfall(request.getRainfall());
            log.setRecommendedCrop(response.getRecommendedCrop());
            log.setSuitabilityScore(response.getSuitabilityScore());
            log.setReasoning(response.getReason());
            log.setCultivationTips(response.getCultivationTips());
            log.setSdgAlignment(response.getSdgAlignment());

            cropLogRepository.save(log);
        } catch (Exception e) {
            System.err.println("Note: Could not save crop log to DB: " + e.getMessage());
        }

        return response;
    }

    public List<CropRecommendationLog> getHistory(Long userId) {
        if (userId != null) {
            return cropLogRepository.findByUserIdOrderByCreatedAtDesc(userId);
        }
        return cropLogRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
