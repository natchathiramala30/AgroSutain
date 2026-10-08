package com.agrosustain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sdg_metrics")
public class SdgMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "sdg2_yield_efficiency")
    private Double sdg2YieldEfficiency = 88.5;

    @Column(name = "sdg6_water_saved_liters")
    private Double sdg6WaterSavedLiters = 45000.0;

    @Column(name = "sdg12_fertilizer_reduction_pct")
    private Double sdg12FertilizerReductionPct = 22.0;

    @Column(name = "sdg13_carbon_offset_kg")
    private Double sdg13CarbonOffsetKg = 310.0;

    @Column(name = "sdg15_soil_health_index")
    private Double sdg15SoilHealthIndex = 91.0;

    @Column(name = "overall_sustainability_score")
    private Double overallSustainabilityScore = 89.2;

    @Column(name = "evaluation_month", length = 30)
    private String evaluationMonth = "October 2026";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public SdgMetric() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Double getSdg2YieldEfficiency() { return sdg2YieldEfficiency; }
    public void setSdg2YieldEfficiency(Double sdg2YieldEfficiency) { this.sdg2YieldEfficiency = sdg2YieldEfficiency; }

    public Double getSdg6WaterSavedLiters() { return sdg6WaterSavedLiters; }
    public void setSdg6WaterSavedLiters(Double sdg6WaterSavedLiters) { this.sdg6WaterSavedLiters = sdg6WaterSavedLiters; }

    public Double getSdg12FertilizerReductionPct() { return sdg12FertilizerReductionPct; }
    public void setSdg12FertilizerReductionPct(Double sdg12FertilizerReductionPct) { this.sdg12FertilizerReductionPct = sdg12FertilizerReductionPct; }

    public Double getSdg13CarbonOffsetKg() { return sdg13CarbonOffsetKg; }
    public void setSdg13CarbonOffsetKg(Double sdg13CarbonOffsetKg) { this.sdg13CarbonOffsetKg = sdg13CarbonOffsetKg; }

    public Double getSdg15SoilHealthIndex() { return sdg15SoilHealthIndex; }
    public void setSdg15SoilHealthIndex(Double sdg15SoilHealthIndex) { this.sdg15SoilHealthIndex = sdg15SoilHealthIndex; }

    public Double getOverallSustainabilityScore() { return overallSustainabilityScore; }
    public void setOverallSustainabilityScore(Double overallSustainabilityScore) { this.overallSustainabilityScore = overallSustainabilityScore; }

    public String getEvaluationMonth() { return evaluationMonth; }
    public void setEvaluationMonth(String evaluationMonth) { this.evaluationMonth = evaluationMonth; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
