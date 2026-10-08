package com.agrosustain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "yield_predictions")
public class YieldPredictionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "crop_name", length = 80)
    private String cropName;

    @Column(name = "area_acres")
    private Double areaAcres;

    @Column(name = "soil_type", length = 50)
    private String soilType;

    @Column(name = "fertilizer_usage_kg")
    private Double fertilizerUsageKg;

    @Column(name = "rainfall_mm")
    private Double rainfallMm;

    @Column(name = "predicted_yield_tons")
    private Double predictedYieldTons;

    @Column(name = "predicted_yield_per_acre")
    private Double predictedYieldPerAcre;

    @Column(name = "estimated_revenue_usd")
    private Double estimatedRevenueUsd;

    @Column(name = "sdg2_food_production_impact")
    private String sdg2FoodProductionImpact;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public YieldPredictionLog() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

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

    public Double getPredictedYieldTons() { return predictedYieldTons; }
    public void setPredictedYieldTons(Double predictedYieldTons) { this.predictedYieldTons = predictedYieldTons; }

    public Double getPredictedYieldPerAcre() { return predictedYieldPerAcre; }
    public void setPredictedYieldPerAcre(Double predictedYieldPerAcre) { this.predictedYieldPerAcre = predictedYieldPerAcre; }

    public Double getEstimatedRevenueUsd() { return estimatedRevenueUsd; }
    public void setEstimatedRevenueUsd(Double estimatedRevenueUsd) { this.estimatedRevenueUsd = estimatedRevenueUsd; }

    public String getSdg2FoodProductionImpact() { return sdg2FoodProductionImpact; }
    public void setSdg2FoodProductionImpact(String sdg2FoodProductionImpact) { this.sdg2FoodProductionImpact = sdg2FoodProductionImpact; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
