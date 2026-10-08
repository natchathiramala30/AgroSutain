package com.agrosustain.dto;

public class DiseaseResponse {
    private boolean success;
    private String crop;
    private String diseaseName;
    private String pathogenType;
    private Double confidence;
    private String severity;
    private String symptoms;
    private String suggestedAction;
    private String preventionTips;
    private String organicRemedy;
    private String chemicalRemedy;
    private String sdgImpact;
    private String disclaimer;

    public DiseaseResponse() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getCrop() { return crop; }
    public void setCrop(String crop) { this.crop = crop; }

    public String getDiseaseName() { return diseaseName; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }

    public String getPathogenType() { return pathogenType; }
    public void setPathogenType(String pathogenType) { this.pathogenType = pathogenType; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getSuggestedAction() { return suggestedAction; }
    public void setSuggestedAction(String suggestedAction) { this.suggestedAction = suggestedAction; }

    public String getPreventionTips() { return preventionTips; }
    public void setPreventionTips(String preventionTips) { this.preventionTips = preventionTips; }

    public String getOrganicRemedy() { return organicRemedy; }
    public void setOrganicRemedy(String organicRemedy) { this.organicRemedy = organicRemedy; }

    public String getChemicalRemedy() { return chemicalRemedy; }
    public void setChemicalRemedy(String chemicalRemedy) { this.chemicalRemedy = chemicalRemedy; }

    public String getSdgImpact() { return sdgImpact; }
    public void setSdgImpact(String sdgImpact) { this.sdgImpact = sdgImpact; }

    public String getDisclaimer() { return disclaimer; }
    public void setDisclaimer(String disclaimer) { this.disclaimer = disclaimer; }
}
