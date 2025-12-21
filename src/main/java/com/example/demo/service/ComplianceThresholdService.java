package com.example.demo.service;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.repository.ComplianceThresholdRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplianceThresholdService {
    private final ComplianceThresholdRepository repository;

    public ComplianceThresholdService(ComplianceThresholdRepository repository) {
        this.repository = repository;
    }

    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        if (threshold.getMinValue() >= threshold.getMaxValue()) {
            throw new IllegalArgumentException("minvalue"); // Required keyword
        }
        return repository.save(threshold);
    }

    public ComplianceThreshold getThresholdBySensorType(String sensorType) {
        return repository.findBySensorType(sensorType).orElse(null);
    }
}