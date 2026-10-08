package com.agrosustain.controller;

import com.agrosustain.dto.CropRequest;
import com.agrosustain.dto.CropResponse;
import com.agrosustain.model.CropRecommendationLog;
import com.agrosustain.service.CropService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@CrossOrigin(origins = "*")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping("/recommend")
    public ResponseEntity<CropResponse> recommendCrop(@RequestBody CropRequest request) {
        CropResponse response = cropService.getRecommendation(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<List<CropRecommendationLog>> getHistory(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(cropService.getHistory(userId));
    }
}
