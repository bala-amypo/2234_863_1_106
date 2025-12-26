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

        String status = "UNSAFE";
        if (reading.getReadingValue() >= threshold.getMinValue() && reading.getReadingValue() <= threshold.getMaxValue()) {
            status = "SAFE";
        }

        List<ComplianceLog> existingLogs = complianceLogRepository.findBySensorReading_Id(readingId);
        ComplianceLog log;
        if (!existingLogs.isEmpty()) {
            log = existingLogs.get(0);
            log.setStatusAssigned(status);
            log.setThresholdUsed(threshold);
            log.setLoggedAt(LocalDateTime.now());
            log.setRemarks("Re-evaluated");
        } else {
            log = new ComplianceLog();
            log.setSensorReading(reading);
            log.setThresholdUsed(threshold);
            log.setStatusAssigned(status);
            log.setLoggedAt(LocalDateTime.now());
            log.setRemarks("Evaluated");
        }

        reading.setStatus(status);
        sensorReadingRepository.save(reading);

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
