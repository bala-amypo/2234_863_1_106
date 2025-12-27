package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {
    private final ComplianceThresholdRepository thresholdRepository;
    
    public ComplianceThresholdServiceImpl(ComplianceThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }
    
    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        if (threshold.getMinValue() >= threshold.getMaxValue()) {
            throw new IllegalArgumentException("minValue must be less than maxValue");
        }
        return thresholdRepository.save(threshold);
    }
    
    @Override
    public ComplianceThreshold getThresholdBySensorType(String sensorType) {
        return thresholdRepository.findBySensorType(sensorType)
            .orElseThrow(() -> new RuntimeException("Threshold not found"));
    }
}