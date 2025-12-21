package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance")
@Tag(name = "Compliance Evaluation", description = "Endpoints for evaluating readings and retrieving compliance logs")
public class ComplianceEvaluationController {

    private final ComplianceEvaluationService evaluationService;

    public ComplianceEvaluationController(ComplianceEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate/{readingId}")
    @Operation(summary = "Evaluate Reading", description = "Evaluate a specific reading against compliance thresholds")
    public ResponseEntity<ComplianceLog> evaluateReading(@PathVariable Long readingId) {
        return ResponseEntity.ok(evaluationService.evaluateReading(readingId));
    }

    @GetMapping("/reading/{readingId}")
    @Operation(summary = "Get Logs by Reading", description = "Retrieve compliance logs for a specific reading")
    public ResponseEntity<List<ComplianceLog>> getLogsByReading(@PathVariable Long readingId) {
        return ResponseEntity.ok(evaluationService.getLogsByReading(readingId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Log by ID", description = "Retrieve a specific compliance log by its ID")
    public ResponseEntity<ComplianceLog> getLog(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getLog(id));
    }
}
