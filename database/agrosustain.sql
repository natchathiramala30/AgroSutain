-- ==========================================================
-- AgroSustain - AI-Powered SDG Smart Agriculture Platform
-- Database Schema & Sample Initial Seed Data
-- Compatible with MySQL 5.7+ / MySQL 8.0+ / MariaDB
-- ==========================================================

CREATE DATABASE IF NOT EXISTS agrosustain_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE agrosustain_db;

-- ----------------------------------------------------------
-- 1. Users Table
-- ----------------------------------------------------------
DROP TABLE IF EXISTS sdg_metrics;
DROP TABLE IF EXISTS yield_predictions;
DROP TABLE IF EXISTS irrigation_schedules;
DROP TABLE IF EXISTS disease_detections;
DROP TABLE IF EXISTS crop_recommendations;
DROP TABLE IF EXISTS weather_advisories;
DROP TABLE IF EXISTS farm_profiles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    phone_number VARCHAR(25),
    password_hash VARCHAR(255) NOT NULL,
    location VARCHAR(120),
    farm_size_acres DECIMAL(8, 2) DEFAULT 5.00,
    primary_soil_type VARCHAR(50) DEFAULT 'Loamy',
    role VARCHAR(20) DEFAULT 'ROLE_FARMER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------
-- 2. Crop Recommendations Log
-- ----------------------------------------------------------
CREATE TABLE crop_recommendations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    nitrogen DECIMAL(6, 2) NOT NULL,
    phosphorus DECIMAL(6, 2) NOT NULL,
    potassium DECIMAL(6, 2) NOT NULL,
    temperature DECIMAL(5, 2) NOT NULL,
    humidity DECIMAL(5, 2) NOT NULL,
    soil_ph DECIMAL(4, 2) NOT NULL,
    rainfall DECIMAL(7, 2) NOT NULL,
    recommended_crop VARCHAR(80) NOT NULL,
    suitability_score DECIMAL(5, 2) NOT NULL,
    reasoning TEXT,
    cultivation_tips TEXT,
    sdg_alignment VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------
-- 3. Plant Disease Detections Log
-- ----------------------------------------------------------
CREATE TABLE disease_detections (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    crop_name VARCHAR(80) NOT NULL,
    image_file_name VARCHAR(255),
    disease_name VARCHAR(120) NOT NULL,
    confidence_score DECIMAL(5, 2) NOT NULL,
    symptoms TEXT,
    suggested_action TEXT,
    prevention_tips TEXT,
    severity_level VARCHAR(30) DEFAULT 'Moderate',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------
-- 4. Smart Irrigation Schedules & Guidance
-- ----------------------------------------------------------
CREATE TABLE irrigation_schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    crop_type VARCHAR(80) NOT NULL,
    soil_type VARCHAR(50) NOT NULL,
    soil_moisture_pct DECIMAL(5, 2) NOT NULL,
    area_acres DECIMAL(6, 2) NOT NULL,
    evapotranspiration_rate DECIMAL(5, 2),
    water_required_liters DECIMAL(12, 2) NOT NULL,
    recommended_runtime_minutes INT NOT NULL,
    irrigation_method VARCHAR(50) DEFAULT 'Drip Irrigation',
    water_savings_pct DECIMAL(5, 2) DEFAULT 35.00,
    status VARCHAR(30) DEFAULT 'Scheduled',
    scheduled_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------
-- 5. Crop Yield Predictions
-- ----------------------------------------------------------
CREATE TABLE yield_predictions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    crop_name VARCHAR(80) NOT NULL,
    area_acres DECIMAL(8, 2) NOT NULL,
    soil_type VARCHAR(50) NOT NULL,
    fertilizer_usage_kg DECIMAL(8, 2) NOT NULL,
    rainfall_mm DECIMAL(7, 2) NOT NULL,
    predicted_yield_tons DECIMAL(8, 2) NOT NULL,
    predicted_yield_per_acre DECIMAL(8, 2) NOT NULL,
    estimated_revenue_usd DECIMAL(12, 2),
    sdg2_food_production_impact VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------
-- 6. SDG Sustainability Metrics & Tracking
-- ----------------------------------------------------------
CREATE TABLE sdg_metrics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    sdg2_yield_efficiency DECIMAL(5, 2) DEFAULT 88.50,
    sdg6_water_saved_liters DECIMAL(12, 2) DEFAULT 45000.00,
    sdg12_fertilizer_reduction_pct DECIMAL(5, 2) DEFAULT 22.00,
    sdg13_carbon_offset_kg DECIMAL(8, 2) DEFAULT 310.00,
    sdg15_soil_health_index DECIMAL(5, 2) DEFAULT 91.00,
    overall_sustainability_score DECIMAL(5, 2) DEFAULT 89.20,
    evaluation_month VARCHAR(20) DEFAULT 'October 2026',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------------------------------------
-- 7. Weather Advisory Cache & Alerts
-- ----------------------------------------------------------
CREATE TABLE weather_advisories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(120) NOT NULL,
    temperature_celsius DECIMAL(5, 2) NOT NULL,
    humidity_pct DECIMAL(5, 2) NOT NULL,
    wind_speed_kmh DECIMAL(5, 2) NOT NULL,
    weather_condition VARCHAR(80) NOT NULL,
    precipitation_prob_pct DECIMAL(5, 2) NOT NULL,
    advisory_alert TEXT,
    spraying_suitable BOOLEAN DEFAULT TRUE,
    harvesting_suitable BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ==========================================================
-- Initial Seed Data
-- ==========================================================

-- Demo User: password is 'farmer123' (BCrypt: $2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi)
INSERT INTO users (id, full_name, email, phone_number, password_hash, location, farm_size_acres, primary_soil_type, role)
VALUES 
(1, 'Green Valley Farmer', 'farmer@agrosustain.org', '+1 555 019 2834', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'Midwest Ag Zone, Springfield', 15.50, 'Alluvial / Loam', 'ROLE_FARMER');

-- Sample Crop Recommendations
INSERT INTO crop_recommendations (user_id, nitrogen, phosphorus, potassium, temperature, humidity, soil_ph, rainfall, recommended_crop, suitability_score, reasoning, cultivation_tips, sdg_alignment)
VALUES 
(1, 90.0, 42.0, 43.0, 24.5, 82.0, 6.5, 202.0, 'Rice', 94.5, 'Optimal nitrogen content and humid tropical climate provide exceptional conditions for wetland rice paddy.', 'Maintain 3-5 cm water layer in vegetative stage. Adopt System of Rice Intensification (SRI) for 40% water saving.', 'SDG 2 (Zero Hunger) & SDG 6 (Clean Water)'),
(1, 60.0, 55.0, 45.0, 26.0, 65.0, 6.8, 110.0, 'Maize', 89.0, 'Moderate rainfall, warm temperature and balanced phosphorus-potassium profile ideal for hybrid corn.', 'Plant at 60cm row spacing. Use split nitrogen application to minimize runoff.', 'SDG 2 (Food Security) & SDG 12 (Responsible Inputs)'),
(1, 20.0, 60.0, 20.0, 18.0, 16.0, 7.2, 75.0, 'Chickpea', 91.2, 'Low nitrogen requirement due to biological N-fixation; well-suited for winter/rabi semi-arid conditions.', 'Requires minimal irrigation. Inoculate with Rhizobium culture to enrich soil health naturally.', 'SDG 15 (Life on Land) & SDG 13 (Low Carbon)');

-- Sample Plant Disease Detections
INSERT INTO disease_detections (user_id, crop_name, image_file_name, disease_name, confidence_score, symptoms, suggested_action, prevention_tips, severity_level)
VALUES
(1, 'Tomato', 'tomato_early_blight_sample.jpg', 'Tomato Early Blight (Alternaria solani)', 92.4, 'Concentric ring brown spots on lower mature leaves with yellow chlorotic halos.', 'Prune infected lower foliage immediately. Apply copper-based bio-fungicide or neem oil extract.', 'Practice 3-year crop rotation with non-solanaceous crops and use drip irrigation to avoid wet foliage.', 'Moderate'),
(1, 'Rice', 'rice_blast_sample.jpg', 'Rice Blast (Magnaporthe oryzae)', 88.7, 'Diamond or spindle-shaped lesions with greyish center and reddish-brown borders.', 'Drain excess standing water temporarily and spray Pseudomonas fluorescens bio-agent.', 'Avoid excessive nitrogen fertilization and use resistant cultivar seeds.', 'High'),
(1, 'Wheat', 'wheat_rust_sample.jpg', 'Healthy Plant - No Disease Detected', 97.1, 'Vibrant green leaf blades with uniform chlorophyll distribution and strong turgidity.', 'Maintain standard organic nutrient schedule and monitor weekly.', 'Continue regular prophylactic neem spray every 15 days.', 'None');

-- Sample Smart Irrigation Schedules
INSERT INTO irrigation_schedules (user_id, crop_type, soil_type, soil_moisture_pct, area_acres, evapotranspiration_rate, water_required_liters, recommended_runtime_minutes, irrigation_method, water_savings_pct, status)
VALUES
(1, 'Maize', 'Loamy', 34.0, 10.0, 4.8, 18500.0, 75, 'Precision Drip', 38.5, 'Completed'),
(1, 'Tomato', 'Clay Loam', 28.5, 3.5, 5.2, 9200.0, 45, 'Sub-surface Drip', 42.0, 'Active'),
(1, 'Chickpea', 'Sandy Loam', 42.0, 2.0, 3.5, 2400.0, 20, 'Micro Sprinkler', 30.0, 'Scheduled');

-- Sample Crop Yield Predictions
INSERT INTO yield_predictions (user_id, crop_name, area_acres, soil_type, fertilizer_usage_kg, rainfall_mm, predicted_yield_tons, predicted_yield_per_acre, estimated_revenue_usd, sdg2_food_production_impact)
VALUES
(1, 'Rice', 10.0, 'Alluvial', 180.0, 210.0, 42.50, 4.25, 14875.00, 'Produces enough grain to feed ~140 people for 1 year'),
(1, 'Maize', 5.5, 'Loamy', 120.0, 115.0, 24.75, 4.50, 6930.00, 'High energy grain contribution to local food supply chains');

-- Sample SDG Sustainability Metrics
INSERT INTO sdg_metrics (user_id, sdg2_yield_efficiency, sdg6_water_saved_liters, sdg12_fertilizer_reduction_pct, sdg13_carbon_offset_kg, sdg15_soil_health_index, overall_sustainability_score, evaluation_month)
VALUES
(1, 92.40, 142500.00, 28.50, 840.00, 94.00, 91.80, 'October 2026');

-- Sample Weather Advisory
INSERT INTO weather_advisories (location, temperature_celsius, humidity_pct, wind_speed_kmh, weather_condition, precipitation_prob_pct, advisory_alert, spraying_suitable, harvesting_suitable)
VALUES
('Midwest Ag Zone', 24.5, 68.0, 11.2, 'Partly Cloudy', 15.0, 'Optimal weather window for bio-fertilizer foliar application and field weeding. Low disease humidity risk.', TRUE, TRUE);
