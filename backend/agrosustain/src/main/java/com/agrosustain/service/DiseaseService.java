package com.agrosustain.service;

import com.agrosustain.dto.DiseaseResponse;
import com.agrosustain.model.DiseaseDetectionLog;
import com.agrosustain.model.User;
import com.agrosustain.repository.DiseaseLogRepository;
import com.agrosustain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DiseaseService {

    private final AiClientService aiClientService;
    private final DiseaseLogRepository diseaseLogRepository;
    private final UserRepository userRepository;

    public DiseaseService(AiClientService aiClientService, DiseaseLogRepository diseaseLogRepository, UserRepository userRepository) {
        this.aiClientService = aiClientService;
        this.diseaseLogRepository = diseaseLogRepository;
        this.userRepository = userRepository;
    }

    public DiseaseResponse detectDisease(MultipartFile file, String cropHint, Long userId) {
        byte[] bytes = null;
        String filename = "sample_leaf.jpg";
        if (file != null && !file.isEmpty()) {
            try {
                bytes = file.getBytes();
                filename = file.getOriginalFilename();
            } catch (Exception e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        DiseaseResponse response = aiClientService.detectPlantDisease(bytes, filename, cropHint);

        // Persist to database
        try {
            DiseaseDetectionLog log = new DiseaseDetectionLog();
            if (userId != null) {
                User user = userRepository.findById(userId).orElse(null);
                log.setUser(user);
            }
            log.setCropName(response.getCrop());
            log.setImageFileName(filename);
            log.setDiseaseName(response.getDiseaseName());
            log.setConfidenceScore(response.getConfidence());
            log.setSymptoms(response.getSymptoms());
            log.setSuggestedAction(response.getSuggestedAction());
            log.setPreventionTips(response.getPreventionTips());
            log.setSeverityLevel(response.getSeverity());

            diseaseLogRepository.save(log);
        } catch (Exception e) {
            System.err.println("Note: Could not save disease log to DB: " + e.getMessage());
        }

        return response;
    }

    public List<DiseaseDetectionLog> getHistory(Long userId) {
        if (userId != null) {
            return diseaseLogRepository.findByUserIdOrderByCreatedAtDesc(userId);
        }
        return diseaseLogRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
