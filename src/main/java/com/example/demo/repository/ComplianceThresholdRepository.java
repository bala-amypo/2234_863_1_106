package com.example.demo.repository;

import com.example.demo.entity.ComplianceThreshold;
import java.util.Optional;
import java.util.List;

public interface ComplianceThresholdRepository {
    ComplianceThreshold save(ComplianceThreshold threshold);
    Optional<ComplianceThreshold> findById(Long id);
    List<ComplianceThreshold> findAll();
    Optional<ComplianceThreshold> findBySensorType(String sensorType);
}