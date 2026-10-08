package com.agrosustain.controller;

import com.agrosustain.dto.WeatherResponse;
import com.agrosustain.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/advisory")
    public ResponseEntity<WeatherResponse> getWeatherAdvisory(@RequestParam(required = false, defaultValue = "Midwest Agricultural Zone") String location) {
        WeatherResponse response = weatherService.getWeatherAdvisory(location);
        return ResponseEntity.ok(response);
    }
}
