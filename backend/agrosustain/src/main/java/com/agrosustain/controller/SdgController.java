package com.agrosustain.controller;

import com.agrosustain.dto.SdgImpactSummaryDTO;
import com.agrosustain.service.SdgService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sdg")
@CrossOrigin(origins = "*")
public class SdgController {

    private final SdgService sdgService;

    public SdgController(SdgService sdgService) {
        this.sdgService = sdgService;
    }

    @GetMapping("/impact-summary")
    public ResponseEntity<SdgImpactSummaryDTO> getImpactSummary(@RequestParam(required = false) Long userId) {
        SdgImpactSummaryDTO response = sdgService.getSdgImpact(userId);
        return ResponseEntity.ok(response);
    }
}
