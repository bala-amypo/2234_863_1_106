package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ComplianceEvaluationService {
    private final SensorReadingRepository readingRepo;
    private final ComplianceThresholdRepository thresholdRepo;
    private final ComplianceLogRepository logRepo;

    public ComplianceEvaluationService(SensorReadingRepository readingRepo, 
                                      ComplianceThresholdRepository thresholdRepo, 
                                      ComplianceLogRepository logRepo) {
        this.readingRepo = readingRepo;
        this.thresholdRepo = thresholdRepo;
        this.logRepo = logRepo;
    }

    public ComplianceLog evaluateReading(Long readingId) {
        SensorReading reading = readingRepo.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        ComplianceThreshold threshold = thresholdRepo.findBySensorType(reading.getSensor().getSensorType())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        ComplianceLog log = new ComplianceLog();
        log.setSensorReading(reading);
        log.setThresholdUsed(threshold);
        
        // Logic: Compliant if value is within min/max
        if (reading.getReadingValue() >= threshold.getMinValue() && reading.getReadingValue() <= threshold.getMaxValue()) {
            log.setStatusAssigned("COMPLIANT");
        } else {
            log.setStatusAssigned("NON-COMPLIANT");
        }
        
        return logRepo.save(log);
    }
}