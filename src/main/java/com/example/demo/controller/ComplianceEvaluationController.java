package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceEvaluationController {
    private final ComplianceEvaluationService evaluationService;

    public ComplianceEvaluationController(ComplianceEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate/{readingId}")
    public ResponseEntity<ComplianceLog> evaluateReading(@PathVariable Long readingId) {
        ComplianceLog log = evaluationService.evaluateReading(readingId);
        return ResponseEntity.ok(log);
    }
}
