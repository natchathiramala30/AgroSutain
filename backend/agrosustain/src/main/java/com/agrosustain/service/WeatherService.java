package com.agrosustain.service;

import com.agrosustain.dto.WeatherResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WeatherService {

    public WeatherResponse getWeatherAdvisory(String location) {
        String loc = (location != null && !location.trim().isEmpty()) ? location.trim() : "Regional Agricultural Zone";
        
        WeatherResponse resp = new WeatherResponse();
        resp.setSuccess(true);
        resp.setLocation(loc);
        resp.setCurrentTemperature(24.8);
        resp.setHumidity(68.0);
        resp.setWindSpeedKmh(11.4);
        resp.setWeatherCondition("Partly Cloudy with Gentle Breeze");
        resp.setIcon("cloud-sun");
        resp.setPrecipitationProbability(18.0);
        resp.setSoilMoistureEstimate(36.5);
        resp.setSprayingSuitable(true);
        resp.setHarvestingSuitable(true);
        resp.setAgroAdvisory(
            "Optimal weather window for bio-fertilizer foliar application and field weeding. " +
            "Low fungal disease sporulation risk for the next 48 hours. Wind speeds (<15 km/h) are safe for precision spray drift control."
        );

        List<Map<String, Object>> forecast = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEE, MMM d");

        String[] conditions = {"Partly Cloudy", "Clear & Sunny", "Scattered Showers", "Overcast", "Sunny & Warm"};
        double[] temps = {25.2, 27.0, 23.5, 22.8, 28.1};
        double[] hums = {65.0, 58.0, 82.0, 74.0, 55.0};
        double[] rains = {15.0, 5.0, 75.0, 35.0, 10.0};
        String[] advisories = {
            "Excellent day for soil aeration and field preparation.",
            "Ideal for harvesting and solar grain drying. Peak solar radiation.",
            "Rain expected. Suspend nitrogen top-dressing and pesticide spraying.",
            "Moderate cloud cover. Good for transplanting seedlings without heat shock.",
            "Warm dry weather. Check drip lines and irrigate early morning."
        };

        for (int i = 0; i < 5; i++) {
            Map<String, Object> day = new HashMap<>();
            day.put("date", today.plusDays(i).format(fmt));
            day.put("dayName", today.plusDays(i).getDayOfWeek().name());
            day.put("temperature", temps[i]);
            day.put("humidity", hums[i]);
            day.put("condition", conditions[i]);
            day.put("precipitationProbability", rains[i]);
            day.put("advisory", advisories[i]);
            day.put("sprayingSuitable", rains[i] < 30.0);
            forecast.add(day);
        }

        resp.setForecast5Days(forecast);
        return resp;
    }
}
