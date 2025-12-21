package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compliance")
@Tag(name = "Compliance Evaluation Endpoints")
public class ComplianceEvaluationController {

    private final ComplianceEvaluationService evaluationService;

    public ComplianceEvaluationController(ComplianceEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate/{readingId}")
    public ComplianceLog evaluateReading(@PathVariable Long readingId) {
        return evaluationService.evaluateReading(readingId);
    }

    @GetMapping("/{id}")
    public ComplianceLog getLog(@PathVariable Long id) {
        return evaluationService.getLog(id);
    }
}