package com.example.demo.repository;

import com.example.demo.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    List<SensorReading> findBySensor_Id(Long id);
    List<SensorReading> findBySensor_IdAndReadingTimeBetween(Long id, LocalDateTime start, LocalDateTime end);
}