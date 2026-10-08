package com.agrosustain.dto;

import java.util.List;

public class YieldRequest {
    private Long userId;
    private String cropName = "Maize";
    private Double areaAcres = 5.0;
    private String soilType = "Loamy";
    private Double fertilizerUsageKg = 140.0;
    private Double rainfallMm = 120.0;
    private String irrigationMethod = "Precision Drip";

    public YieldRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }

    public Double getAreaAcres() { return areaAcres; }
    public void setAreaAcres(Double areaAcres) { this.areaAcres = areaAcres; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public Double getFertilizerUsageKg() { return fertilizerUsageKg; }
    public void setFertilizerUsageKg(Double fertilizerUsageKg) { this.fertilizerUsageKg = fertilizerUsageKg; }

    public Double getRainfallMm() { return rainfallMm; }
    public void setRainfallMm(Double rainfallMm) { this.rainfallMm = rainfallMm; }

    public String getIrrigationMethod() { return irrigationMethod; }
    public void setIrrigationMethod(String irrigationMethod) { this.irrigationMethod = irrigationMethod; }
}
