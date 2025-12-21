package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_logs")
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_reading_id")
    private SensorReading sensorReading;

    @ManyToOne
    @JoinColumn(name = "threshold_used_id")
    private ComplianceThreshold thresholdUsed;

    private String statusAssigned; // SAFE, UNSAFE

    private String remarks;

    private LocalDateTime loggedAt;

    public ComplianceLog() {
    }

    public ComplianceLog(SensorReading sensorReading, ComplianceThreshold thresholdUsed, String statusAssigned, String remarks, LocalDateTime loggedAt) {
        this.sensorReading = sensorReading;
        this.thresholdUsed = thresholdUsed;
        this.statusAssigned = statusAssigned;
        this.remarks = remarks;
        this.loggedAt = loggedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensorReading getSensorReading() {
        return sensorReading;
    }

    public void setSensorReading(SensorReading sensorReading) {
        this.sensorReading = sensorReading;
    }

    public ComplianceThreshold getThresholdUsed() {
        return thresholdUsed;
    }

    public void setThresholdUsed(ComplianceThreshold thresholdUsed) {
        this.thresholdUsed = thresholdUsed;
    }

    public String getStatusAssigned() {
        return statusAssigned;
    }

    public void setStatusAssigned(String statusAssigned) {
        this.statusAssigned = statusAssigned;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }
}
