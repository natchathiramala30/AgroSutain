package com.agrosustain.service;

import com.agrosustain.dto.DashboardSummaryDTO;
import com.agrosustain.model.*;
import com.agrosustain.repository.*;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final CropLogRepository cropLogRepository;
    private final DiseaseLogRepository diseaseLogRepository;
    private final IrrigationRepository irrigationRepository;
    private final YieldLogRepository yieldLogRepository;
    private final SdgMetricRepository sdgMetricRepository;

    public DashboardService(UserRepository userRepository,
                            CropLogRepository cropLogRepository,
                            DiseaseLogRepository diseaseLogRepository,
                            IrrigationRepository irrigationRepository,
                            YieldLogRepository yieldLogRepository,
                            SdgMetricRepository sdgMetricRepository) {
        this.userRepository = userRepository;
        this.cropLogRepository = cropLogRepository;
        this.diseaseLogRepository = diseaseLogRepository;
        this.irrigationRepository = irrigationRepository;
        this.yieldLogRepository = yieldLogRepository;
        this.sdgMetricRepository = sdgMetricRepository;
    }

    public DashboardSummaryDTO getDashboardSummary(Long userId) {
        User user = (userId != null) ? userRepository.findById(userId).orElse(null) : null;
        if (user == null) {
            user = userRepository.findAll().stream().findFirst().orElse(null);
        }

        DashboardSummaryDTO dto = new DashboardSummaryDTO();
        dto.setSuccess(true);
        dto.setFarmerName(user != null ? user.getFullName() : "Green Valley Farmer");
        dto.setFarmLocation(user != null ? user.getLocation() : "Midwest Agricultural Zone");
        dto.setFarmSizeAcres(user != null ? user.getFarmSizeAcres() : 15.5);

        // Fetch latest crop recommendation
        List<CropRecommendationLog> cropLogs = (user != null)
                ? cropLogRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                : cropLogRepository.findTop5ByOrderByCreatedAtDesc();
        if (!cropLogs.isEmpty()) {
            dto.setRecommendedCrop(cropLogs.get(0).getRecommendedCrop());
            dto.setCropSuitability(cropLogs.get(0).getSuitabilityScore());
        } else {
            dto.setRecommendedCrop("Rice (Paddy)");
            dto.setCropSuitability(94.5);
        }

        // Weather
        dto.setCurrentWeather("Partly Cloudy, 24.8°C");
        dto.setCurrentTemperature(24.8);

        // Irrigation
        List<IrrigationSchedule> irLogs = (user != null)
                ? irrigationRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                : irrigationRepository.findTop5ByOrderByCreatedAtDesc();
        if (!irLogs.isEmpty()) {
            dto.setIrrigationStatus("Active (" + irLogs.get(0).getRecommendedRuntimeMinutes() + " mins scheduled)");
            dto.setSoilMoisture(irLogs.get(0).getSoilMoisturePct());
        } else {
            dto.setIrrigationStatus("Optimal (Drip scheduled)");
            dto.setSoilMoisture(34.0);
        }

        // Disease
        List<DiseaseDetectionLog> disLogs = (user != null)
                ? diseaseLogRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                : diseaseLogRepository.findTop5ByOrderByCreatedAtDesc();
        if (!disLogs.isEmpty()) {
            dto.setDiseaseStatus(disLogs.get(0).getDiseaseName());
        } else {
            dto.setDiseaseStatus("Healthy - Low Pathogen Pressure");
        }

        // Yield
        List<YieldPredictionLog> yLogs = (user != null)
                ? yieldLogRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                : yieldLogRepository.findTop5ByOrderByCreatedAtDesc();
        if (!yLogs.isEmpty()) {
            dto.setExpectedYieldTons(yLogs.get(0).getPredictedYieldTons());
        } else {
            dto.setExpectedYieldTons(42.5);
        }

        // SDG Score
        dto.setSustainabilityScore(91.8);

        // Chart 1: Water Usage (Weekly Liters saved vs conventional flood)
        Map<String, Object> waterChart = new HashMap<>();
        waterChart.put("labels", Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        waterChart.put("smartDripUsage", Arrays.asList(18200, 16400, 0, 14200, 15800, 0, 19100));
        waterChart.put("conventionalFloodUsage", Arrays.asList(32000, 31000, 29000, 33000, 31500, 28000, 34000));
        dto.setWaterUsageChart(waterChart);

        // Chart 2: Crop Yield Comparison
        Map<String, Object> yieldChart = new HashMap<>();
        yieldChart.put("labels", Arrays.asList("Rice", "Maize", "Wheat", "Chickpea", "Tomato"));
        yieldChart.put("predictedTonsPerAcre", Arrays.asList(4.25, 4.50, 3.40, 1.85, 15.20));
        yieldChart.put("regionalBenchmarkTonsPerAcre", Arrays.asList(3.60, 3.80, 2.90, 1.40, 12.00));
        dto.setCropYieldChart(yieldChart);

        // Chart 3: Resource Distribution
        Map<String, Object> resourceChart = new HashMap<>();
        resourceChart.put("labels", Arrays.asList("Bio-Fertilizer", "Precision Irrigation", "Renewable Solar Pumping", "IPM Biological Control", "Recycled Biomass Mulch"));
        resourceChart.put("percentages", Arrays.asList(28, 32, 18, 12, 10));
        dto.setResourceDistributionChart(resourceChart);

        // Chart 4: SDG 5-Pillar Sustainability Radar
        Map<String, Object> sdgRadar = new HashMap<>();
        sdgRadar.put("labels", Arrays.asList("SDG 2: Zero Hunger", "SDG 6: Clean Water", "SDG 12: Responsible Inputs", "SDG 13: Climate Action", "SDG 15: Life on Land"));
        sdgRadar.put("scores", Arrays.asList(92.4, 95.0, 88.5, 90.5, 94.0));
        dto.setSdgRadarChart(sdgRadar);

        // Recent Activity Feed
        List<Map<String, Object>> activities = new ArrayList<>();
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("MMM d, h:mm a");

        Map<String, Object> act1 = new HashMap<>();
        act1.put("title", "AI Crop Recommendation Run");
        act1.put("desc", "Optimal match found: Rice (94.5% suitability for high rainfall season)");
        act1.put("time", "12 mins ago");
        act1.put("icon", "fa-wheat-awn");
        act1.put("color", "text-success");
        activities.add(act1);

        Map<String, Object> act2 = new HashMap<>();
        act2.put("title", "Smart Irrigation Automated Schedule");
        act2.put("desc", "Scheduled 45 min drip cycle for Zone 2 (Saved 8,400 Liters)");
        act2.put("time", "2 hours ago");
        act2.put("icon", "fa-droplet");
        act2.put("color", "text-info");
        activities.add(act2);

        Map<String, Object> act3 = new HashMap<>();
        act3.put("title", "Leaf Pathology Scan Completed");
        act3.put("desc", "Identified early blight symptom on Tomato (Trichoderma remedy recommended)");
        act3.put("time", "Yesterday");
        act3.put("icon", "fa-microscope");
        act3.put("color", "text-warning");
        activities.add(act3);

        dto.setRecentActivities(activities);

        // Active Alerts
        dto.setActiveAlerts(Arrays.asList(
            "Optimal 48-hour spraying window active: Low wind speed (<12 km/h) and moderate humidity.",
            "SDG Water Savings Milestone Reached: 142,500 Liters conserved this season!",
            "Soil moisture in Field Sector B is currently 34% (Approaching refill point)."
        ));

        return dto;
    }
}
