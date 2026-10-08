package com.agrosustain.controller;

import com.agrosustain.dto.DiseaseResponse;
import com.agrosustain.model.DiseaseDetectionLog;
import com.agrosustain.service.DiseaseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/disease")
@CrossOrigin(origins = "*")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping(value = "/detect", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DiseaseResponse> detectDisease(
            @RequestParam(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "crop", required = false) String cropHint,
            @RequestParam(value = "userId", required = false) Long userId) {

        DiseaseResponse response = diseaseService.detectDisease(file, cropHint, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<List<DiseaseDetectionLog>> getHistory(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(diseaseService.getHistory(userId));
    }
}
