package com.agrosustain.dto;

import java.util.List;
import java.util.Map;

public class WeatherResponse {
    private boolean success;
    private String location;
    private Double currentTemperature;
    private Double humidity;
    private Double windSpeedKmh;
    private String weatherCondition;
    private String icon;
    private Double precipitationProbability;
    private Double soilMoistureEstimate;
    private String agroAdvisory;
    private boolean sprayingSuitable;
    private boolean harvestingSuitable;
    private List<Map<String, Object>> forecast5Days;

    public WeatherResponse() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getCurrentTemperature() { return currentTemperature; }
    public void setCurrentTemperature(Double currentTemperature) { this.currentTemperature = currentTemperature; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public Double getWindSpeedKmh() { return windSpeedKmh; }
    public void setWindSpeedKmh(Double windSpeedKmh) { this.windSpeedKmh = windSpeedKmh; }

    public String getWeatherCondition() { return weatherCondition; }
    public void setWeatherCondition(String weatherCondition) { this.weatherCondition = weatherCondition; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public Double getPrecipitationProbability() { return precipitationProbability; }
    public void setPrecipitationProbability(Double precipitationProbability) { this.precipitationProbability = precipitationProbability; }

    public Double getSoilMoistureEstimate() { return soilMoistureEstimate; }
    public void setSoilMoistureEstimate(Double soilMoistureEstimate) { this.soilMoistureEstimate = soilMoistureEstimate; }

    public String getAgroAdvisory() { return agroAdvisory; }
    public void setAgroAdvisory(String agroAdvisory) { this.agroAdvisory = agroAdvisory; }

    public boolean isSprayingSuitable() { return sprayingSuitable; }
    public void setSprayingSuitable(boolean sprayingSuitable) { this.sprayingSuitable = sprayingSuitable; }

    public boolean isHarvestingSuitable() { return harvestingSuitable; }
    public void setHarvestingSuitable(boolean harvestingSuitable) { this.harvestingSuitable = harvestingSuitable; }

    public List<Map<String, Object>> getForecast5Days() { return forecast5Days; }
    public void setForecast5Days(List<Map<String, Object>> forecast5Days) { this.forecast5Days = forecast5Days; }
}
