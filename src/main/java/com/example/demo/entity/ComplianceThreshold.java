package com.example.demo.entity;

public class ComplianceThreshold {
    private Long id;
    private String sensorType;
    private Double minValue;
    private Double maxValue;
    private String severityLevel;
    
    public ComplianceThreshold() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }
    
    public Double getMinValue() { return minValue; }
    public void setMinValue(Double minValue) { this.minValue = minValue; }
    
    public Double getMaxValue() { return maxValue; }
    public void setMaxValue(Double maxValue) { this.maxValue = maxValue; }
    
    public String getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(String severityLevel) { this.severityLevel = severityLevel; }
}