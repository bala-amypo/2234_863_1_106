package com.example.demo.repository;

import com.example.demo.entity.SensorReading;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SensorReadingRepository {
    SensorReading save(SensorReading reading);
    Optional<SensorReading> findById(Long id);
    List<SensorReading> findAll();
    List<SensorReading> findBySensor_Id(Long sensorId);
    List<SensorReading> findBySensor_IdAndReadingTimeBetween(Long sensorId, LocalDateTime start, LocalDateTime end);
}