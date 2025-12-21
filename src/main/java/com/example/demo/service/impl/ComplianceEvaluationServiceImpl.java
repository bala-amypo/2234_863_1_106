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

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final SensorReadingRepository readingRepository;
    private final ComplianceThresholdRepository thresholdRepository;
    private final ComplianceLogRepository logRepository;

    public ComplianceEvaluationServiceImpl(SensorReadingRepository readingRepository, ComplianceThresholdRepository thresholdRepository, ComplianceLogRepository logRepository) {
        this.readingRepository = readingRepository;
        this.thresholdRepository = thresholdRepository;
        this.logRepository = logRepository;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        SensorReading reading = readingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        if (reading.getSensor() == null) {
             throw new ResourceNotFoundException("Sensor not found for reading");
        }

        String sensorType = reading.getSensor().getSensorType();
        List<ComplianceThreshold> thresholds =
        thresholdRepository.findBySensorType(sensorType);

if (thresholds.isEmpty()) {
    throw new RuntimeException("No threshold found for sensor type: " + sensorType);
}

ComplianceThreshold threshold = thresholds.get(0);

        String status;
        if (reading.getReadingValue() >= threshold.getMinValue() && reading.getReadingValue() <= threshold.getMaxValue()) {
            status = "SAFE";
        } else {
            status = "UNSAFE";
        }
        
        // Update reading status? Requirement says "status is determined automatically based on threshold evaluation" for Reading.
        // It says "statusAssigned must match evaluated condition" for Log.
        // It's good practice to update the reading status too if logic matches. 
        reading.setStatus(status);
        readingRepository.save(reading);

        // Check if log exists
        // Requirement: "Check if log already exists for reading; if yes, update it; if no, create new."
        // We need a way to find log by reading.
        List<ComplianceLog> existingLogs = logRepository.findBySensorReading_Id(readingId);
        ComplianceLog log;
        if (!existingLogs.isEmpty()) {
            log = existingLogs.get(0); // Assume one per reading as per rules
            log.setThresholdUsed(threshold);
            log.setStatusAssigned(status);
            log.setLoggedAt(LocalDateTime.now());
        } else {
            log = new ComplianceLog();
            log.setSensorReading(reading);
            log.setThresholdUsed(threshold);
            log.setStatusAssigned(status);
            log.setLoggedAt(LocalDateTime.now());
        }

        return logRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return logRepository.findBySensorReading_Id(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
