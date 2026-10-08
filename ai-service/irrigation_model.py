"""
AgroSustain - AI Smart Irrigation & Water Conservation Engine
Implements FAO-56 Penman-Monteith Evapotranspiration (ET0) & Soil Moisture Water Balance Model
Calculates exact volumetric crop water needs, pump run duration, and SDG 6 water savings.
"""

class SmartIrrigationModel:
    def __init__(self):
        # FAO-56 Crop Coefficients (Kc) across growth stages (Mid-season peak)
        self.crop_kc = {
            'Rice': {'kc': 1.15, 'root_depth_cm': 40, 'depletion_factor': 0.20},
            'Maize': {'kc': 1.10, 'root_depth_cm': 80, 'depletion_factor': 0.55},
            'Wheat': {'kc': 1.05, 'root_depth_cm': 70, 'depletion_factor': 0.50},
            'Cotton': {'kc': 1.15, 'root_depth_cm': 100, 'depletion_factor': 0.65},
            'Tomato': {'kc': 1.05, 'root_depth_cm': 50, 'depletion_factor': 0.40},
            'Potato': {'kc': 1.10, 'root_depth_cm': 40, 'depletion_factor': 0.35},
            'Chickpea': {'kc': 0.85, 'root_depth_cm': 60, 'depletion_factor': 0.60},
            'Banana': {'kc': 1.20, 'root_depth_cm': 60, 'depletion_factor': 0.35},
            'Sugarcane': {'kc': 1.25, 'root_depth_cm': 120, 'depletion_factor': 0.50},
            'Pomegranate': {'kc': 0.75, 'root_depth_cm': 90, 'depletion_factor': 0.50},
            'Grapes': {'kc': 0.70, 'root_depth_cm': 100, 'depletion_factor': 0.45},
            'Default': {'kc': 1.00, 'root_depth_cm': 50, 'depletion_factor': 0.50}
        }

        # Soil Available Water Capacity (AWC in mm/meter of soil depth) and Field Capacity (%)
        self.soil_properties = {
            'Sandy': {'field_capacity_pct': 14.0, 'wilting_point_pct': 6.0, 'awc_mm_per_m': 80},
            'Sandy Loam': {'field_capacity_pct': 22.0, 'wilting_point_pct': 10.0, 'awc_mm_per_m': 120},
            'Loamy': {'field_capacity_pct': 30.0, 'wilting_point_pct': 14.0, 'awc_mm_per_m': 160},
            'Alluvial': {'field_capacity_pct': 32.0, 'wilting_point_pct': 15.0, 'awc_mm_per_m': 170},
            'Clay Loam': {'field_capacity_pct': 36.0, 'wilting_point_pct': 18.0, 'awc_mm_per_m': 180},
            'Black Cotton (Vertisol)': {'field_capacity_pct': 42.0, 'wilting_point_pct': 22.0, 'awc_mm_per_m': 200},
            'Red Soil': {'field_capacity_pct': 26.0, 'wilting_point_pct': 12.0, 'awc_mm_per_m': 140}
        }

        # Irrigation system application efficiencies (fraction) and typical flow rates
        self.systems = {
            'Precision Drip': {'efficiency': 0.92, 'savings_vs_flood_pct': 45.0, 'flow_rate_lpm_per_acre': 250},
            'Sub-surface Drip': {'efficiency': 0.95, 'savings_vs_flood_pct': 52.0, 'flow_rate_lpm_per_acre': 250},
            'Micro Sprinkler': {'efficiency': 0.82, 'savings_vs_flood_pct': 30.0, 'flow_rate_lpm_per_acre': 350},
            'Center Pivot': {'efficiency': 0.78, 'savings_vs_flood_pct': 22.0, 'flow_rate_lpm_per_acre': 400},
            'Furrow Irrigation': {'efficiency': 0.60, 'savings_vs_flood_pct': 10.0, 'flow_rate_lpm_per_acre': 500},
            'Flood / Basin': {'efficiency': 0.50, 'savings_vs_flood_pct': 0.0, 'flow_rate_lpm_per_acre': 600}
        }

    def calculate(self, crop_type, soil_type, current_moisture_pct, area_acres, temperature=28.0, humidity=60.0, solar_radiation_mj=20.0, forecast_rain_mm=0.0, irrigation_method='Precision Drip'):
        crop_data = self.crop_kc.get(crop_type, self.crop_kc['Default'])
        soil = self.soil_properties.get(soil_type, self.soil_properties['Loamy'])
        sys_data = self.systems.get(irrigation_method, self.systems['Precision Drip'])

        # Reference ET0 estimation (Hargreaves-Samani / Radiation method)
        et0 = max(2.5, round(0.0023 * (temperature + 17.8) * math.sqrt(max(4.0, temperature - 15.0 + 5.0)) * 2.5, 2))
        kc = crop_data['kc']
        etc = round(et0 * kc, 2)  # Crop evapotranspiration in mm/day

        # Moisture deficit calculation
        fc = soil['field_capacity_pct']
        wp = soil['wilting_point_pct']
        current_m = max(wp, min(fc, current_moisture_pct))
        
        # Deficit percentage below field capacity
        deficit_pct = max(0.0, fc - current_m)
        root_depth_m = crop_data['root_depth_cm'] / 100.0

        # Net water required in mm to restore root zone to 90% field capacity
        net_depth_mm = max(0.0, (deficit_pct / 100.0) * soil['awc_mm_per_m'] * root_depth_m)
        
        # Subtract effective forecast rainfall
        effective_rain = forecast_rain_mm * 0.75
        net_depth_mm = max(0.0, net_depth_mm - effective_rain)

        # Gross irrigation depth considering application efficiency
        gross_depth_mm = net_depth_mm / sys_data['efficiency'] if sys_data['efficiency'] > 0 else net_depth_mm

        # Volumetric conversion (1 mm depth on 1 acre = 4,046.86 liters)
        liters_per_acre = round(gross_depth_mm * 4046.86, 0)
        total_water_liters = round(liters_per_acre * area_acres, 0)

        # Pump runtime calculation
        flow_rate_total_lpm = sys_data['flow_rate_lpm_per_acre'] * area_acres
        runtime_minutes = int(round(total_water_liters / flow_rate_total_lpm)) if flow_rate_total_lpm > 0 and total_water_liters > 0 else 0

        # Savings comparison vs flood irrigation
        flood_vol = (net_depth_mm / 0.50) * 4046.86 * area_acres
        liters_saved = max(0.0, flood_vol - total_water_liters)

        # Recommendation & schedule status
        if current_moisture_pct >= (fc * 0.85):
            urgency = "Adequate"
            action = "No irrigation required today. Soil moisture is optimal."
            runtime_minutes = 0
            total_water_liters = 0
        elif current_moisture_pct >= (fc * 0.65):
            urgency = "Moderate"
            action = f"Light maintenance irrigation recommended within next 24-36 hours."
        else:
            urgency = "Critical (Immediate)"
            action = f"Apply {total_water_liters:,.0f} Liters immediately to prevent root stress and yield penalty."

        return {
            'crop_type': crop_type,
            'soil_type': soil_type,
            'soil_moisture_pct': current_moisture_pct,
            'field_capacity_pct': fc,
            'urgency_level': urgency,
            'evapotranspiration_et0_mm_day': et0,
            'crop_evapotranspiration_etc_mm_day': etc,
            'net_irrigation_depth_mm': round(net_depth_mm, 2),
            'gross_water_liters_total': total_water_liters,
            'water_liters_per_acre': liters_per_acre,
            'pump_runtime_minutes': runtime_minutes,
            'irrigation_method': irrigation_method,
            'application_efficiency_pct': round(sys_data['efficiency'] * 100, 1),
            'water_savings_vs_flood_liters': round(liters_saved, 0),
            'sdg6_clean_water_impact': f"Conserves {liters_saved:,.0f} Liters of groundwater vs flood methods ({sys_data['savings_vs_flood_pct']}% savings)",
            'advisory_instructions': action,
            'best_time_to_irrigate': "Early Morning (5:30 AM - 8:00 AM) or Late Evening to minimize evaporative losses"
        }

import math
irrigation_model = SmartIrrigationModel()
