package com.agrosustain.dto;

public class IrrigationResponse {
    private boolean success;
    private String cropType;
    private String soilType;
    private Double soilMoisturePct;
    private Double fieldCapacityPct;
    private String urgencyLevel;
    private Double evapotranspirationEt0MmDay;
    private Double cropEvapotranspirationEtcMmDay;
    private Double netIrrigationDepthMm;
    private Double grossWaterLitersTotal;
    private Double waterLitersPerAcre;
    private Integer pumpRuntimeMinutes;
    private String irrigationMethod;
    private Double applicationEfficiencyPct;
    private Double waterSavingsVsFloodLiters;
    private String sdg6CleanWaterImpact;
    private String advisoryInstructions;
    private String bestTimeToIrrigate;

    public IrrigationResponse() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getCropType() { return cropType; }
    public void setCropType(String cropType) { this.cropType = cropType; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public Double getSoilMoisturePct() { return soilMoisturePct; }
    public void setSoilMoisturePct(Double soilMoisturePct) { this.soilMoisturePct = soilMoisturePct; }

    public Double getFieldCapacityPct() { return fieldCapacityPct; }
    public void setFieldCapacityPct(Double fieldCapacityPct) { this.fieldCapacityPct = fieldCapacityPct; }

    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }

    public Double getEvapotranspirationEt0MmDay() { return evapotranspirationEt0MmDay; }
    public void setEvapotranspirationEt0MmDay(Double evapotranspirationEt0MmDay) { this.evapotranspirationEt0MmDay = evapotranspirationEt0MmDay; }

    public Double getCropEvapotranspirationEtcMmDay() { return cropEvapotranspirationEtcMmDay; }
    public void setCropEvapotranspirationEtcMmDay(Double cropEvapotranspirationEtcMmDay) { this.cropEvapotranspirationEtcMmDay = cropEvapotranspirationEtcMmDay; }

    public Double getNetIrrigationDepthMm() { return netIrrigationDepthMm; }
    public void setNetIrrigationDepthMm(Double netIrrigationDepthMm) { this.netIrrigationDepthMm = netIrrigationDepthMm; }

    public Double getGrossWaterLitersTotal() { return grossWaterLitersTotal; }
    public void setGrossWaterLitersTotal(Double grossWaterLitersTotal) { this.grossWaterLitersTotal = grossWaterLitersTotal; }

    public Double getWaterLitersPerAcre() { return waterLitersPerAcre; }
    public void setWaterLitersPerAcre(Double waterLitersPerAcre) { this.waterLitersPerAcre = waterLitersPerAcre; }

    public Integer getPumpRuntimeMinutes() { return pumpRuntimeMinutes; }
    public void setPumpRuntimeMinutes(Integer pumpRuntimeMinutes) { this.pumpRuntimeMinutes = pumpRuntimeMinutes; }

    public String getIrrigationMethod() { return irrigationMethod; }
    public void setIrrigationMethod(String irrigationMethod) { this.irrigationMethod = irrigationMethod; }

    public Double getApplicationEfficiencyPct() { return applicationEfficiencyPct; }
    public void setApplicationEfficiencyPct(Double applicationEfficiencyPct) { this.applicationEfficiencyPct = applicationEfficiencyPct; }

    public Double getWaterSavingsVsFloodLiters() { return waterSavingsVsFloodLiters; }
    public void setWaterSavingsVsFloodLiters(Double waterSavingsVsFloodLiters) { this.waterSavingsVsFloodLiters = waterSavingsVsFloodLiters; }

    public String getSdg6CleanWaterImpact() { return sdg6CleanWaterImpact; }
    public void setSdg6CleanWaterImpact(String sdg6CleanWaterImpact) { this.sdg6CleanWaterImpact = sdg6CleanWaterImpact; }

    public String getAdvisoryInstructions() { return advisoryInstructions; }
    public void setAdvisoryInstructions(String advisoryInstructions) { this.advisoryInstructions = advisoryInstructions; }

    public String getBestTimeToIrrigate() { return bestTimeToIrrigate; }
    public void setBestTimeToIrrigate(String bestTimeToIrrigate) { this.bestTimeToIrrigate = bestTimeToIrrigate; }
}
