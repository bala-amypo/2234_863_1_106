package com.example.demo.repository;

import com.example.demo.entity.ComplianceLog;
import java.util.List;
import java.util.Optional;

public interface ComplianceLogRepository {
    ComplianceLog save(ComplianceLog log);
    Optional<ComplianceLog> findById(Long id);
    List<ComplianceLog> findAll();
    List<ComplianceLog> findBySensorReading_Id(Long sensorReadingId);
}