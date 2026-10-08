package com.agrosustain.dto;

import java.util.List;
import java.util.Map;

public class DashboardSummaryDTO {
    private boolean success;
    private String farmerName;
    private String farmLocation;
    private Double farmSizeAcres;
    private String recommendedCrop;
    private Double cropSuitability;
    private String currentWeather;
    private Double currentTemperature;
    private String irrigationStatus;
    private Double soilMoisture;
    private String diseaseStatus;
    private Double expectedYieldTons;
    private Double sustainabilityScore;
    
    // Chart datasets
    private Map<String, Object> waterUsageChart;
    private Map<String, Object> cropYieldChart;
    private Map<String, Object> resourceDistributionChart;
    private Map<String, Object> sdgRadarChart;
    
    // Activity Feeds
    private List<Map<String, Object>> recentActivities;
    private List<String> activeAlerts;

    public DashboardSummaryDTO() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getFarmerName() { return farmerName; }
    public void setFarmerName(String farmerName) { this.farmerName = farmerName; }

    public String getFarmLocation() { return farmLocation; }
    public void setFarmLocation(String farmLocation) { this.farmLocation = farmLocation; }

    public Double getFarmSizeAcres() { return farmSizeAcres; }
    public void setFarmSizeAcres(Double farmSizeAcres) { this.farmSizeAcres = farmSizeAcres; }

    public String getRecommendedCrop() { return recommendedCrop; }
    public void setRecommendedCrop(String recommendedCrop) { this.recommendedCrop = recommendedCrop; }

    public Double getCropSuitability() { return cropSuitability; }
    public void setCropSuitability(Double cropSuitability) { this.cropSuitability = cropSuitability; }

    public String getCurrentWeather() { return currentWeather; }
    public void setCurrentWeather(String currentWeather) { this.currentWeather = currentWeather; }

    public Double getCurrentTemperature() { return currentTemperature; }
    public void setCurrentTemperature(Double currentTemperature) { this.currentTemperature = currentTemperature; }

    public String getIrrigationStatus() { return irrigationStatus; }
    public void setIrrigationStatus(String irrigationStatus) { this.irrigationStatus = irrigationStatus; }

    public Double getSoilMoisture() { return soilMoisture; }
    public void setSoilMoisture(Double soilMoisture) { this.soilMoisture = soilMoisture; }

    public String getDiseaseStatus() { return diseaseStatus; }
    public void setDiseaseStatus(String diseaseStatus) { this.diseaseStatus = diseaseStatus; }

    public Double getExpectedYieldTons() { return expectedYieldTons; }
    public void setExpectedYieldTons(Double expectedYieldTons) { this.expectedYieldTons = expectedYieldTons; }

    public Double getSustainabilityScore() { return sustainabilityScore; }
    public void setSustainabilityScore(Double sustainabilityScore) { this.sustainabilityScore = sustainabilityScore; }

    public Map<String, Object> getWaterUsageChart() { return waterUsageChart; }
    public void setWaterUsageChart(Map<String, Object> waterUsageChart) { this.waterUsageChart = waterUsageChart; }

    public Map<String, Object> getCropYieldChart() { return cropYieldChart; }
    public void setCropYieldChart(Map<String, Object> cropYieldChart) { this.cropYieldChart = cropYieldChart; }

    public Map<String, Object> getResourceDistributionChart() { return resourceDistributionChart; }
    public void setResourceDistributionChart(Map<String, Object> resourceDistributionChart) { this.resourceDistributionChart = resourceDistributionChart; }

    public Map<String, Object> getSdgRadarChart() { return sdgRadarChart; }
    public void setSdgRadarChart(Map<String, Object> sdgRadarChart) { this.sdgRadarChart = sdgRadarChart; }

    public List<Map<String, Object>> getRecentActivities() { return recentActivities; }
    public void setRecentActivities(List<Map<String, Object>> recentActivities) { this.recentActivities = recentActivities; }

    public List<String> getActiveAlerts() { return activeAlerts; }
    public void setActiveAlerts(List<String> activeAlerts) { this.activeAlerts = activeAlerts; }
}
