package com.example.demo.repository;

import com.example.demo.entity.Sensor;
import java.util.Optional;
import java.util.List;

public interface SensorRepository {
    Sensor save(Sensor sensor);
    Optional<Sensor> findById(Long id);
    List<Sensor> findAll();
    Optional<Sensor> findBySensorCode(String sensorCode);
    List<Sensor> findByLocation_Region(String region);
}