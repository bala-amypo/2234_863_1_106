
package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {
    private final SensorReadingService readingService;

    public SensorReadingController(SensorReadingService readingService) {
        this.readingService = readingService;
    }

    @PostMapping("/sensor/{sensorId}")
    public ResponseEntity<SensorReading> submitReading(@PathVariable Long sensorId, @RequestBody SensorReading reading) {
        SensorReading created = readingService.submitReading(sensorId, reading);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorReading> getReading(@PathVariable Long id) {
        SensorReading reading = readingService.getReading(id);
        return ResponseEntity.ok(reading);
    }
}
