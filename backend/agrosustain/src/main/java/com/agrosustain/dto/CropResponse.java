package com.agrosustain.dto;

import java.util.List;
import java.util.Map;

public class CropResponse {
    private boolean success;
    private String recommendedCrop;
    private Double suitabilityScore;
    private Double confidence;
    private String reason;
    private String cultivationTips;
    private String sdgAlignment;
    private Map<String, Object> parametersAnalyzed;
    private List<Map<String, Object>> alternativeCrops;

    public CropResponse() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getRecommendedCrop() { return recommendedCrop; }
    public void setRecommendedCrop(String recommendedCrop) { this.recommendedCrop = recommendedCrop; }

    public Double getSuitabilityScore() { return suitabilityScore; }
    public void setSuitabilityScore(Double suitabilityScore) { this.suitabilityScore = suitabilityScore; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getCultivationTips() { return cultivationTips; }
    public void setCultivationTips(String cultivationTips) { this.cultivationTips = cultivationTips; }

    public String getSdgAlignment() { return sdgAlignment; }
    public void setSdgAlignment(String sdgAlignment) { this.sdgAlignment = sdgAlignment; }

    public Map<String, Object> getParametersAnalyzed() { return parametersAnalyzed; }
    public void setParametersAnalyzed(Map<String, Object> parametersAnalyzed) { this.parametersAnalyzed = parametersAnalyzed; }

    public List<Map<String, Object>> getAlternativeCrops() { return alternativeCrops; }
    public void setAlternativeCrops(List<Map<String, Object>> alternativeCrops) { this.alternativeCrops = alternativeCrops; }
}
