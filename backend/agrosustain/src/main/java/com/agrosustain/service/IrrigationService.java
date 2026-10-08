package com.agrosustain.service;

import com.agrosustain.dto.IrrigationRequest;
import com.agrosustain.dto.IrrigationResponse;
import com.agrosustain.model.IrrigationSchedule;
import com.agrosustain.model.User;
import com.agrosustain.repository.IrrigationRepository;
import com.agrosustain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrrigationService {

    private final AiClientService aiClientService;
    private final IrrigationRepository irrigationRepository;
    private final UserRepository userRepository;

    public IrrigationService(AiClientService aiClientService, IrrigationRepository irrigationRepository, UserRepository userRepository) {
        this.aiClientService = aiClientService;
        this.irrigationRepository = irrigationRepository;
        this.userRepository = userRepository;
    }

    public IrrigationResponse calculateSchedule(IrrigationRequest request) {
        IrrigationResponse response = aiClientService.calculateIrrigation(request);

        try {
            IrrigationSchedule schedule = new IrrigationSchedule();
            if (request.getUserId() != null) {
                User user = userRepository.findById(request.getUserId()).orElse(null);
                schedule.setUser(user);
            }
            schedule.setCropType(response.getCropType());
            schedule.setSoilType(response.getSoilType());
            schedule.setSoilMoisturePct(response.getSoilMoisturePct());
            schedule.setAreaAcres(request.getAreaAcres());
            schedule.setEvapotranspirationRate(response.getCropEvapotranspirationEtcMmDay());
            schedule.setWaterRequiredLiters(response.getGrossWaterLitersTotal());
            schedule.setRecommendedRuntimeMinutes(response.getPumpRuntimeMinutes());
            schedule.setIrrigationMethod(response.getIrrigationMethod());
            schedule.setWaterSavingsPct(response.getApplicationEfficiencyPct());
            schedule.setStatus("Scheduled");

            irrigationRepository.save(schedule);
        } catch (Exception e) {
            System.err.println("Note: Could not save irrigation schedule to DB: " + e.getMessage());
        }

        return response;
    }

    public List<IrrigationSchedule> getSchedules(Long userId) {
        if (userId != null) {
            return irrigationRepository.findByUserIdOrderByCreatedAtDesc(userId);
        }
        return irrigationRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
