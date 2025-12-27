package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thresholds")
public class ComplianceThresholdController {
    private final ComplianceThresholdService thresholdService;

    public ComplianceThresholdController(ComplianceThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    @PostMapping
    public ResponseEntity<ComplianceThreshold> createThreshold(@RequestBody ComplianceThreshold threshold) {
        ComplianceThreshold created = thresholdService.createThreshold(threshold);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/sensor-type/{sensorType}")
    public ResponseEntity<ComplianceThreshold> getThresholdBySensorType(@PathVariable String sensorType) {
        ComplianceThreshold threshold = thresholdService.getThresholdBySensorType(sensorType);
        return ResponseEntity.ok(threshold);
    }
}
