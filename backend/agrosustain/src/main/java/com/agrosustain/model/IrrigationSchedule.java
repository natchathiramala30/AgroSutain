package com.agrosustain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "irrigation_schedules")
public class IrrigationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "crop_type", length = 80)
    private String cropType;

    @Column(name = "soil_type", length = 50)
    private String soilType;

    @Column(name = "soil_moisture_pct")
    private Double soilMoisturePct;

    @Column(name = "area_acres")
    private Double areaAcres;

    @Column(name = "evapotranspiration_rate")
    private Double evapotranspirationRate;

    @Column(name = "water_required_liters")
    private Double waterRequiredLiters;

    @Column(name = "recommended_runtime_minutes")
    private Integer recommendedRuntimeMinutes;

    @Column(name = "irrigation_method", length = 50)
    private String irrigationMethod = "Precision Drip";

    @Column(name = "water_savings_pct")
    private Double waterSavingsPct = 35.0;

    @Column(length = 30)
    private String status = "Scheduled";

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime = LocalDateTime.now();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public IrrigationSchedule() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCropType() { return cropType; }
    public void setCropType(String cropType) { this.cropType = cropType; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public Double getSoilMoisturePct() { return soilMoisturePct; }
    public void setSoilMoisturePct(Double soilMoisturePct) { this.soilMoisturePct = soilMoisturePct; }

    public Double getAreaAcres() { return areaAcres; }
    public void setAreaAcres(Double areaAcres) { this.areaAcres = areaAcres; }

    public Double getEvapotranspirationRate() { return evapotranspirationRate; }
    public void setEvapotranspirationRate(Double evapotranspirationRate) { this.evapotranspirationRate = evapotranspirationRate; }

    public Double getWaterRequiredLiters() { return waterRequiredLiters; }
    public void setWaterRequiredLiters(Double waterRequiredLiters) { this.waterRequiredLiters = waterRequiredLiters; }

    public Integer getRecommendedRuntimeMinutes() { return recommendedRuntimeMinutes; }
    public void setRecommendedRuntimeMinutes(Integer recommendedRuntimeMinutes) { this.recommendedRuntimeMinutes = recommendedRuntimeMinutes; }

    public String getIrrigationMethod() { return irrigationMethod; }
    public void setIrrigationMethod(String irrigationMethod) { this.irrigationMethod = irrigationMethod; }

    public Double getWaterSavingsPct() { return waterSavingsPct; }
    public void setWaterSavingsPct(Double waterSavingsPct) { this.waterSavingsPct = waterSavingsPct; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
