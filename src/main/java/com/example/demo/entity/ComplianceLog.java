package com.example.demo.entity;

public class ComplianceLog {
    private Long id;
    private SensorReading sensorReading;
    private String statusAssigned;
    
    public ComplianceLog() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public SensorReading getSensorReading() { return sensorReading; }
    public void setSensorReading(SensorReading sensorReading) { this.sensorReading = sensorReading; }
    
    public String getStatusAssigned() { return statusAssigned; }
    public void setStatusAssigned(String statusAssigned) { this.statusAssigned = statusAssigned; }
}