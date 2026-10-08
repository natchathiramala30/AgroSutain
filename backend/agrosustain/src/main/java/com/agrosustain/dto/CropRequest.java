package com.agrosustain.dto;

public class CropRequest {
    private Long userId;
    private Double nitrogen;
    private Double phosphorus;
    private Double potassium;
    private Double temperature;
    private Double humidity;
    private Double soilPh;
    private Double rainfall;

    public CropRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getNitrogen() { return nitrogen != null ? nitrogen : 60.0; }
    public void setNitrogen(Double nitrogen) { this.nitrogen = nitrogen; }

    public Double getPhosphorus() { return phosphorus != null ? phosphorus : 45.0; }
    public void setPhosphorus(Double phosphorus) { this.phosphorus = phosphorus; }

    public Double getPotassium() { return potassium != null ? potassium : 40.0; }
    public void setPotassium(Double potassium) { this.potassium = potassium; }

    public Double getTemperature() { return temperature != null ? temperature : 25.0; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getHumidity() { return humidity != null ? humidity : 70.0; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public Double getSoilPh() { return soilPh != null ? soilPh : 6.5; }
    public void setSoilPh(Double soilPh) { this.soilPh = soilPh; }

    public Double getRainfall() { return rainfall != null ? rainfall : 120.0; }
    public void setRainfall(Double rainfall) { this.rainfall = rainfall; }
}
