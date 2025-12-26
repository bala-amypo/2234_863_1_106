package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final SensorReadingRepository sensorReadingRepository;
    private final ComplianceThresholdRepository thresholdRepository;
    private final ComplianceLogRepository complianceLogRepository;

    public ComplianceEvaluationServiceImpl(SensorReadingRepository sensorReadingRepository,
                                           ComplianceThresholdRepository thresholdRepository,
                                           ComplianceLogRepository complianceLogRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.thresholdRepository = thresholdRepository;
        this.complianceLogRepository = complianceLogRepository;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        String sensorType = reading.getSensor().getSensorType();
        ComplianceThreshold threshold = thresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        String statusAssigned = "UNSAFE";
        if (reading.getReadingValue() >= threshold.getMinValue() && reading.getReadingValue() <= threshold.getMaxValue()) {
            statusAssigned = "SAFE";
        }

        // Check if log already exists
         List<ComplianceLog> existingLogs = complianceLogRepository.findBySensorReading_Id(readingId);
         ComplianceLog log;
         if (!existingLogs.isEmpty()) {
             log = existingLogs.get(0);
             log.setThresholdUsed(threshold);
             log.setStatusAssigned(statusAssigned);
             log.setLoggedAt(LocalDateTime.now());
         } else {
             log = new ComplianceLog();
             log.setSensorReading(reading);
             log.setThresholdUsed(threshold);
             log.setStatusAssigned(statusAssigned);
             log.setLoggedAt(LocalDateTime.now());
             log.setRemarks("Evaluated automatically");
         }

        // Also update reading status? Spec says "status is determined automatically based on threshold evaluation" in 2.5
        // But 6.6 says "evaluateReading ... returns log".
        // I should probably update the reading status too as per 2.5 rule implication, though 6.6 doesn't explicitly say "update sensorReading".
        // 2.5 says "status is determined automatically...". It implies updates.
        // However, 6.6 "Implementation Rules" for evaluateReading doesn't explicitly mention saving SensorReading, only saving ComplianceLog.
        // I will stick to saving ComplianceLog as explicitly described in 6.6.

        return complianceLogRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return complianceLogRepository.findBySensorReading_Id(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
