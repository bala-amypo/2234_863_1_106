package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_thresholds")
public class ComplianceThreshold {
    @ManyToOne
    @JoinColumn(name = "sensor_reading_id") // optional, specifies the DB column
     private SensorReading sensorReading;

public SensorReading getSensorReading() {
    return sensorReading;
}

public void setSensorReading(SensorReading sensorReading) {
    this.sensorReading = sensorReading;
}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sensorType;

    private Double minValue;

    private Double maxValue;

    @Column(nullable = false)
    private String severityLevel; // LOW, MEDIUM, HIGH

    private LocalDateTime createdAt;

    public ComplianceThreshold() {
    }

    public ComplianceThreshold(String sensorType, Double minValue, Double maxValue, String severityLevel, LocalDateTime createdAt) {
        this.sensorType = sensorType;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.severityLevel = severityLevel;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
