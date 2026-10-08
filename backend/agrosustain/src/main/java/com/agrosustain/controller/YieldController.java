package com.agrosustain.controller;

import com.agrosustain.dto.YieldRequest;
import com.agrosustain.dto.YieldResponse;
import com.agrosustain.model.YieldPredictionLog;
import com.agrosustain.service.YieldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yield")
@CrossOrigin(origins = "*")
public class YieldController {

    private final YieldService yieldService;

    public YieldController(YieldService yieldService) {
        this.yieldService = yieldService;
    }

    @PostMapping("/predict")
    public ResponseEntity<YieldResponse> predictYield(@RequestBody YieldRequest request) {
        YieldResponse response = yieldService.predictYield(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<List<YieldPredictionLog>> getHistory(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(yieldService.getHistory(userId));
    }
}
