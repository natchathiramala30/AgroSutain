package com.agrosustain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(length = 25)
    private String phoneNumber;

    @Column(nullable = false)
    private String passwordHash;

    @Column(length = 120)
    private String location;

    @Column(name = "farm_size_acres")
    private Double farmSizeAcres = 5.0;

    @Column(name = "primary_soil_type", length = 50)
    private String primarySoilType = "Loamy";

    @Column(length = 20)
    private String role = "ROLE_FARMER";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}

    public User(String fullName, String email, String phoneNumber, String passwordHash, String location) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.location = location;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getFarmSizeAcres() { return farmSizeAcres; }
    public void setFarmSizeAcres(Double farmSizeAcres) { this.farmSizeAcres = farmSizeAcres; }

    public String getPrimarySoilType() { return primarySoilType; }
    public void setPrimarySoilType(String primarySoilType) { this.primarySoilType = primarySoilType; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
