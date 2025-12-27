package com.example.demo.entity;

import java.time.LocalDateTime;

public class SensorReading {
    private Long id;
    private Double readingValue;
    private LocalDateTime readingTime = LocalDateTime.now();
    private String status = "PENDING";
    private Sensor sensor;
    
    public SensorReading() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getReadingValue() { return readingValue; }
    public void setReadingValue(Double readingValue) { this.readingValue = readingValue; }
    
    public LocalDateTime getReadingTime() { return readingTime; }
    public void setReadingTime(LocalDateTime readingTime) { this.readingTime = readingTime; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Sensor getSensor() { return sensor; }
    public void setSensor(Sensor sensor) { this.sensor = sensor; }
}