package com.agrosustain.controller;

import com.agrosustain.dto.IrrigationRequest;
import com.agrosustain.dto.IrrigationResponse;
import com.agrosustain.model.IrrigationSchedule;
import com.agrosustain.service.IrrigationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/irrigation")
@CrossOrigin(origins = "*")
public class IrrigationController {

    private final IrrigationService irrigationService;

    public IrrigationController(IrrigationService irrigationService) {
        this.irrigationService = irrigationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<IrrigationResponse> calculateIrrigation(@RequestBody IrrigationRequest request) {
        IrrigationResponse response = irrigationService.calculateSchedule(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<IrrigationSchedule>> getSchedules(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(irrigationService.getSchedules(userId));
    }
}
