package com.example.green_house;

public class GreenHouseModel {

    private String id;          
    private Long timestamp;
    private double temperature;
    private double humidity;
    private double soilMoisture;
    private Double co2; 
    
    public GreenHouseModel() {
        
    }

    public GreenHouseModel(String id, Long timestamp, double temperature,
                       double humidity, double soilMoisture, Double co2) {
        this.id = id;
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilMoisture = soilMoisture;
        this.co2 = co2;
    }   

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getSoilMoisture() { return soilMoisture; }
    public void setSoilMoisture(double soilMoisture) { this.soilMoisture = soilMoisture; }

    public Double getCo2() { return co2; }
    public void setCo2(Double co2) { this.co2 = co2; }

}
