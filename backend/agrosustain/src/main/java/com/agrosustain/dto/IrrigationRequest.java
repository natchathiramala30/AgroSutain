package com.agrosustain.dto;

public class IrrigationRequest {
    private Long userId;
    private String cropType = "Maize";
    private String soilType = "Loamy";
    private Double soilMoisturePct = 32.0;
    private Double areaAcres = 5.0;
    private Double temperature = 28.0;
    private Double humidity = 60.0;
    private Double forecastRainMm = 0.0;
    private String irrigationMethod = "Precision Drip";

    public IrrigationRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCropType() { return cropType; }
    public void setCropType(String cropType) { this.cropType = cropType; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public Double getSoilMoisturePct() { return soilMoisturePct; }
    public void setSoilMoisturePct(Double soilMoisturePct) { this.soilMoisturePct = soilMoisturePct; }

    public Double getAreaAcres() { return areaAcres; }
    public void setAreaAcres(Double areaAcres) { this.areaAcres = areaAcres; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public Double getForecastRainMm() { return forecastRainMm; }
    public void setForecastRainMm(Double forecastRainMm) { this.forecastRainMm = forecastRainMm; }

    public String getIrrigationMethod() { return irrigationMethod; }
    public void setIrrigationMethod(String irrigationMethod) { this.irrigationMethod = irrigationMethod; }
}
