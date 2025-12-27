package com.example.demo.service;

import com.example.demo.entity.ComplianceThreshold;

public interface ComplianceThresholdService {
    ComplianceThreshold createThreshold(ComplianceThreshold threshold);
    ComplianceThreshold getThresholdBySensorType(String sensorType);
}