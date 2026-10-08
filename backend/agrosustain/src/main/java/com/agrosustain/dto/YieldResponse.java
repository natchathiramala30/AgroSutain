package com.agrosustain.dto;

import java.util.List;

public class YieldResponse {
    private boolean success;
    private String cropName;
    private Double areaAcres;
    private Double predictedYieldPerAcreTons;
    private Double totalPredictedYieldTons;
    private Double benchmarkYieldTons;
    private Double yieldGainPercentage;
    private Double estimatedGrossRevenueUsd;
    private Double pricePerTonUsd;
    private String sdg2FoodSecurityImpact;
    private String carbonEfficiencyScore;
    private List<String> recommendations;

    public YieldResponse() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }

    public Double getAreaAcres() { return areaAcres; }
    public void setAreaAcres(Double areaAcres) { this.areaAcres = areaAcres; }

    public Double getPredictedYieldPerAcreTons() { return predictedYieldPerAcreTons; }
    public void setPredictedYieldPerAcreTons(Double predictedYieldPerAcreTons) { this.predictedYieldPerAcreTons = predictedYieldPerAcreTons; }

    public Double getTotalPredictedYieldTons() { return totalPredictedYieldTons; }
    public void setTotalPredictedYieldTons(Double totalPredictedYieldTons) { this.totalPredictedYieldTons = totalPredictedYieldTons; }

    public Double getBenchmarkYieldTons() { return benchmarkYieldTons; }
    public void setBenchmarkYieldTons(Double benchmarkYieldTons) { this.benchmarkYieldTons = benchmarkYieldTons; }

    public Double getYieldGainPercentage() { return yieldGainPercentage; }
    public void setYieldGainPercentage(Double yieldGainPercentage) { this.yieldGainPercentage = yieldGainPercentage; }

    public Double getEstimatedGrossRevenueUsd() { return estimatedGrossRevenueUsd; }
    public void setEstimatedGrossRevenueUsd(Double estimatedGrossRevenueUsd) { this.estimatedGrossRevenueUsd = estimatedGrossRevenueUsd; }

    public Double getPricePerTonUsd() { return pricePerTonUsd; }
    public void setPricePerTonUsd(Double pricePerTonUsd) { this.pricePerTonUsd = pricePerTonUsd; }

    public String getSdg2FoodSecurityImpact() { return sdg2FoodSecurityImpact; }
    public void setSdg2FoodSecurityImpact(String sdg2FoodSecurityImpact) { this.sdg2FoodSecurityImpact = sdg2FoodSecurityImpact; }

    public String getCarbonEfficiencyScore() { return carbonEfficiencyScore; }
    public void setCarbonEfficiencyScore(String carbonEfficiencyScore) { this.carbonEfficiencyScore = carbonEfficiencyScore; }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
}
