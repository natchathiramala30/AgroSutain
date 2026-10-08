# 🌾 AgroSustain – AI-Powered SDG Smart Agriculture Platform

[![Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot%203.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Python AI](https://img.shields.io/badge/AI%20Microservice-Flask%20%7C%20Scikit--Learn-blue)](https://flask.palletsprojects.com/)
[![MySQL](https://img.shields.io/badge/Database-MySQL%208.0-orange)](https://www.mysql.com/)
[![UN SDGs](https://img.shields.io/badge/UN%20SDGs-2%2C%206%2C%2012%2C%2013%2C%2015-gold)](https://sdgs.un.org/goals)

**AgroSustain** is a modern, responsive, professional full-stack smart agriculture platform designed to help farmers make better, data-driven, and sustainable decisions. It integrates real-time soil telemetry, machine learning algorithms, plant pathology diagnostics, and agro-meteorological advisories to maximize crop productivity while conserving vital natural resources.

---

## 🌍 UN Sustainable Development Goals (SDG) Alignment

AgroSustain directly advances 5 core United Nations Sustainable Development Goals:

* 🌾 **SDG 2 – Zero Hunger**: Boosts staple crop productivity, predicts harvest yields, and prevents sudden disease losses.
* 💧 **SDG 6 – Clean Water and Sanitation**: Cuts groundwater waste by >40% through FAO-56 evapotranspiration and precision irrigation scheduling.
* ♻️ **SDG 12 – Responsible Consumption and Production**: Replaces chemical pesticide overuse with biological Integrated Pest Management (IPM) and balanced NPK fertilizer recommendations.
* 🌤️ **SDG 13 – Climate Action**: Implements zero-tillage relay cropping, bio-mulching, and climate-resilient crop rotation to sequester organic carbon.
* 🌳 **SDG 15 – Life on Land**: Protects soil biodiversity, halts soil acidification, and prevents land degradation.

---

## 🏗️ Architecture & Data Flow

```
Farmer Browser
     │
     ▼
[ Frontend HTML5 / CSS3 / ES6 / Chart.js ]
     │
     ▼ (REST API calls on :8080)
[ Java Spring Boot Backend ]
     ├── Spring Security (BCrypt Password Hashing)
     ├── Spring Data JPA & Repositories
     └── RestTemplate HTTP Client
     │                         │
     ▼ (Port 3306)             ▼ (Port 5000)
[ MySQL Database ]       [ Python Flask AI Engine ]
                         ├── Random Forest Crop Classifier
                         ├── Computer Vision Disease Detector
                         ├── FAO-56 Irrigation Water Balance
                         └── Crop Yield & Biomass Predictor
```

---

## 📁 Project Structure

```
AgroSustain/
│
├── frontend/
│   ├── index.html                  # Landing page
│   ├── login.html                  # Farmer authentication
│   ├── register.html               # Farmer account registration
│   ├── dashboard.html              # Central telemetry & analytics dashboard
│   ├── crop-recommendation.html    # AI crop recommendation tool
│   ├── disease-detection.html      # Leaf disease scanner
│   ├── weather.html                # 5-Day agricultural forecast & advisory
│   ├── irrigation.html             # Smart irrigation calculator
│   ├── yield-prediction.html       # Harvest yield & gross revenue predictor
│   ├── sdg.html                    # SDG sustainability scorecard
│   │
│   ├── css/
│   │   ├── style.css               # Master modern emerald design system
│   │   ├── dashboard.css           # Sidebar layout, charts & widgets
│   │   └── responsive.css          # Mobile & tablet breakpoints
│   │
│   └── js/
│       ├── main.js                 # Global session & notification router
│       ├── auth.js                 # Authentication logic
│       ├── dashboard.js            # Chart.js visualizations & feeds
│       ├── crop.js                 # Soil sliders & recommendation logic
│       ├── disease.js              # Vision leaf scanning & remedy guides
│       ├── weather.js              # Meteorological radar & forecast
│       ├── irrigation.js           # Evapotranspiration water balance
│       ├── yield.js                # Harvest tonnage & carbon calculator
│       └── sdg.js                  # SDG scorecard & achievement badges
│
├── backend/
│   └── agrosustain/
│       ├── src/main/java/com/agrosustain/
│       │   ├── AgroSustainApplication.java
│       │   ├── config/             # CorsConfig, SecurityConfig, RestTemplateConfig
│       │   ├── controller/         # REST Controllers for all services
│       │   ├── dto/                # Request & Response Data Transfer Objects
│       │   ├── model/              # JPA Entities (User, Crop, Disease, Irrigation, Yield, SDG)
│       │   ├── repository/         # Spring Data JPA Repositories
│       │   └── service/            # Core business logic & AI microservice client
│       │
│       ├── src/main/resources/
│       │   └── application.properties
│       └── pom.xml
│
├── ai-service/
│   ├── app.py                      # Flask REST API entrypoint
│   ├── crop_model.py               # Random Forest Classifier (22 crop varieties)
│   ├── disease_model.py            # Plant pathology vision engine & remedies
│   ├── irrigation_model.py         # FAO-56 Penman-Monteith water model
│   ├── yield_model.py              # Biomass & revenue forecasting model
│   └── requirements.txt
│
├── database/
│   └── agrosustain.sql             # MySQL schema & initial seed data
│
└── README.md
```

---

## 🚀 Quick Start Guide

### 1. Database Setup (MySQL)
1. Open your MySQL client (e.g., MySQL Workbench, phpMyAdmin, or CLI):
   ```sql
   CREATE DATABASE agrosustain_db;
   ```
2. Import `database/agrosustain.sql`:
   ```bash
   mysql -u root -p agrosustain_db < database/agrosustain.sql
   ```

### 2. Python AI Service Setup
1. Navigate to the `ai-service` directory:
   ```bash
   cd ai-service
   ```
2. Install dependencies:
   ```bash
   pip install -r requirements.txt
   ```
3. Start the Flask AI server:
   ```bash
   python app.py
   ```
   *AI Microservice will be running on `http://localhost:5000`.*

### 3. Spring Boot Backend Setup
1. Navigate to the `backend/agrosustain` directory:
   ```bash
   cd backend/agrosustain
   ```
2. Verify database credentials in `src/main/resources/application.properties`.
3. Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
   *REST API will be running on `http://localhost:8080`.*

### 4. Launch Frontend
* Simply open `frontend/index.html` in your favorite web browser, or serve it using Python/Live Server:
  ```bash
  cd frontend
  python -m http.server 3000
  ```
* Open `http://localhost:3000` or `file:///.../frontend/index.html`.

---

## 🔑 Demo Account Credentials

| Role | Email | Password |
| :--- | :--- | :--- |
| **Demo Farmer** | `farmer@agrosustain.org` | `farmer123` |

*(You can also use the 1-Click "Auto Fill" button on the login page for instant access).*

---

## 📡 REST API Reference Table

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register a new farmer profile |
| `POST` | `/api/auth/login` | Secure farmer sign-in |
| `POST` | `/api/crops/recommend` | AI crop recommendation from soil NPK & climate |
| `GET` | `/api/crops/history` | Historical crop recommendations |
| `POST` | `/api/disease/detect` | Multipart plant leaf pathology diagnosis |
| `GET` | `/api/weather/advisory` | 5-day agricultural weather forecast |
| `POST` | `/api/irrigation/calculate` | FAO-56 volumetric water requirement & schedule |
| `POST` | `/api/yield/predict` | Predict crop harvest tonnage and revenue |
| `GET` | `/api/sdg/impact-summary` | Farm sustainability scorecard across 5 SDGs |
| `GET` | `/api/dashboard/summary` | Aggregate telemetry, KPIs, and chart data |

---

## 🧪 Testing the AI Features

1. **Crop Recommendation**: Try adjusting Nitrogen to `90`, Phosphorus to `45`, Potassium to `45`, Rainfall to `200mm`. The model recommends **Rice** with ~94.5% suitability.
2. **Plant Disease Detection**: Select the sample leaf or upload your own leaf photo. It diagnoses the condition (e.g. *Tomato Early Blight*), displays confidence %, and details biological (Trichoderma) + preventive remedies.
3. **Smart Irrigation**: Set soil moisture to `22%` for Maize on 5 acres. The model calculates exact net depth, recommended drip runtime, and water liters conserved vs flood methods.
4. **Yield Prediction**: Predict harvest for 10 acres of Rice on Alluvial soil. The model forecasts ~42.5 tons harvest with revenue valuation and SDG 2 food security impact.

---

## 📜 License
Developed for educational, research, and sustainable agricultural innovation.
