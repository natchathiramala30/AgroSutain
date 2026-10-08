package com.agrosustain.dto;

import java.util.List;
import java.util.Map;

public class SdgImpactSummaryDTO {
    private boolean success;
    private Double overallScore;
    private String sustainabilityGrade;
    private Double sdg2ZeroHungerScore;
    private Double sdg6CleanWaterScore;
    private Double sdg12ResponsibleConsumptionScore;
    private Double sdg13ClimateActionScore;
    private Double sdg15LifeOnLandScore;
    private Double totalWaterSavedLiters;
    private Double carbonOffsetKg;
    private Double chemicalReductionPct;
    private List<Map<String, Object>> badgesUnlocked;
    private List<String> sdgActionRecommendations;

    public SdgImpactSummaryDTO() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public Double getOverallScore() { return overallScore; }
    public void setOverallScore(Double overallScore) { this.overallScore = overallScore; }

    public String getSustainabilityGrade() { return sustainabilityGrade; }
    public void setSustainabilityGrade(String sustainabilityGrade) { this.sustainabilityGrade = sustainabilityGrade; }

    public Double getSdg2ZeroHungerScore() { return sdg2ZeroHungerScore; }
    public void setSdg2ZeroHungerScore(Double sdg2ZeroHungerScore) { this.sdg2ZeroHungerScore = sdg2ZeroHungerScore; }

    public Double getSdg6CleanWaterScore() { return sdg6CleanWaterScore; }
    public void setSdg6CleanWaterScore(Double sdg6CleanWaterScore) { this.sdg6CleanWaterScore = sdg6CleanWaterScore; }

    public Double getSdg12ResponsibleConsumptionScore() { return sdg12ResponsibleConsumptionScore; }
    public void setSdg12ResponsibleConsumptionScore(Double sdg12ResponsibleConsumptionScore) { this.sdg12ResponsibleConsumptionScore = sdg12ResponsibleConsumptionScore; }

    public Double getSdg13ClimateActionScore() { return sdg13ClimateActionScore; }
    public void setSdg13ClimateActionScore(Double sdg13ClimateActionScore) { this.sdg13ClimateActionScore = sdg13ClimateActionScore; }

    public Double getSdg15LifeOnLandScore() { return sdg15LifeOnLandScore; }
    public void setSdg15LifeOnLandScore(Double sdg15LifeOnLandScore) { this.sdg15LifeOnLandScore = sdg15LifeOnLandScore; }

    public Double getTotalWaterSavedLiters() { return totalWaterSavedLiters; }
    public void setTotalWaterSavedLiters(Double totalWaterSavedLiters) { this.totalWaterSavedLiters = totalWaterSavedLiters; }

    public Double getCarbonOffsetKg() { return carbonOffsetKg; }
    public void setCarbonOffsetKg(Double carbonOffsetKg) { this.carbonOffsetKg = carbonOffsetKg; }

    public Double getChemicalReductionPct() { return chemicalReductionPct; }
    public void setChemicalReductionPct(Double chemicalReductionPct) { this.chemicalReductionPct = chemicalReductionPct; }

    public List<Map<String, Object>> getBadgesUnlocked() { return badgesUnlocked; }
    public void setBadgesUnlocked(List<Map<String, Object>> badgesUnlocked) { this.badgesUnlocked = badgesUnlocked; }

    public List<String> getSdgActionRecommendations() { return sdgActionRecommendations; }
    public void setSdgActionRecommendations(List<String> sdgActionRecommendations) { this.sdgActionRecommendations = sdgActionRecommendations; }
}
