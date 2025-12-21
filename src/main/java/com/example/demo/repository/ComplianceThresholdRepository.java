package com.example.demo.repository;

import com.example.demo.entity.ComplianceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplianceThresholdRepository
        extends JpaRepository<ComplianceThreshold, Long> {

    List<ComplianceThreshold> findBySensorReading_Id(Long sensorReadingId);
    List<ComplianceThreshold> findBySensorType(String sensorType);
}
