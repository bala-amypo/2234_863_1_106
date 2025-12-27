package com.example.demo.entity;

public class Sensor {
    private Long id;
    private String sensorCode;
    private String sensorType;
    private Boolean isActive = true;
    private Location location;
    
    public Sensor() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSensorCode() { return sensorCode; }
    public void setSensorCode(String sensorCode) { this.sensorCode = sensorCode; }
    
    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}