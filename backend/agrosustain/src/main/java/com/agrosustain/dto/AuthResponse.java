package com.agrosustain.dto;

public class AuthResponse {
    private boolean success;
    private String message;
    private String token;
    private Long userId;
    private String fullName;
    private String email;
    private String location;
    private Double farmSizeAcres;
    private String primarySoilType;

    public AuthResponse() {}

    public AuthResponse(boolean success, String message, String token, Long userId, String fullName, String email, String location, Double farmSizeAcres, String primarySoilType) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.location = location;
        this.farmSizeAcres = farmSizeAcres;
        this.primarySoilType = primarySoilType;
    }

    public static AuthResponse failure(String message) {
        AuthResponse resp = new AuthResponse();
        resp.setSuccess(false);
        resp.setMessage(message);
        return resp;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getFarmSizeAcres() { return farmSizeAcres; }
    public void setFarmSizeAcres(Double farmSizeAcres) { this.farmSizeAcres = farmSizeAcres; }

    public String getPrimarySoilType() { return primarySoilType; }
    public void setPrimarySoilType(String primarySoilType) { this.primarySoilType = primarySoilType; }
}
