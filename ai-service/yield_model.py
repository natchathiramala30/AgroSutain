"""
AgroSustain - AI Crop Yield & Biomass Prediction Engine
Estimates crop yield per acre, total production, financial gross value, and SDG 2 food security impact
"""

class CropYieldPredictionModel:
    def __init__(self):
        # Base yields in metric tons per acre under standard management
        self.crop_baselines = {
            'Rice': {'base_yield_per_acre': 3.8, 'price_per_ton': 350.0, 'optimal_rain': 200.0, 'optimal_fert': 160.0},
            'Maize': {'base_yield_per_acre': 4.2, 'price_per_ton': 280.0, 'optimal_rain': 100.0, 'optimal_fert': 140.0},
            'Wheat': {'base_yield_per_acre': 3.2, 'price_per_ton': 320.0, 'optimal_rain': 80.0, 'optimal_fert': 130.0},
            'Chickpea': {'base_yield_per_acre': 1.6, 'price_per_ton': 850.0, 'optimal_rain': 70.0, 'optimal_fert': 60.0},
            'Kidney Beans': {'base_yield_per_acre': 1.8, 'price_per_ton': 920.0, 'optimal_rain': 90.0, 'optimal_fert': 70.0},
            'Tomato': {'base_yield_per_acre': 14.5, 'price_per_ton': 450.0, 'optimal_rain': 110.0, 'optimal_fert': 200.0},
            'Potato': {'base_yield_per_acre': 12.0, 'price_per_ton': 380.0, 'optimal_rain': 95.0, 'optimal_fert': 210.0},
            'Cotton': {'base_yield_per_acre': 1.4, 'price_per_ton': 1150.0, 'optimal_rain': 85.0, 'optimal_fert': 150.0},
            'Banana': {'base_yield_per_acre': 18.0, 'price_per_ton': 400.0, 'optimal_rain': 160.0, 'optimal_fert': 250.0},
            'Sugarcane': {'base_yield_per_acre': 35.0, 'price_per_ton': 65.0, 'optimal_rain': 180.0, 'optimal_fert': 280.0},
            'Pomegranate': {'base_yield_per_acre': 5.5, 'price_per_ton': 1200.0, 'optimal_rain': 100.0, 'optimal_fert': 110.0},
            'Grapes': {'base_yield_per_acre': 8.0, 'price_per_ton': 950.0, 'optimal_rain': 70.0, 'optimal_fert': 160.0}
        }

        self.soil_multipliers = {
            'Alluvial': 1.12,
            'Loamy': 1.10,
            'Clay Loam': 1.05,
            'Black Cotton (Vertisol)': 1.08,
            'Red Soil': 0.94,
            'Sandy Loam': 0.90,
            'Sandy': 0.78,
            'Saline': 0.65
        }

        self.irrigation_multipliers = {
            'Precision Drip': 1.22,
            'Sub-surface Drip': 1.25,
            'Micro Sprinkler': 1.15,
            'Center Pivot': 1.10,
            'Furrow Irrigation': 0.95,
            'Flood / Basin': 0.90,
            'Rainfed / Non-irrigated': 0.75
        }

    def predict(self, crop_name, area_acres, soil_type, fertilizer_kg_per_acre, rainfall_mm, irrigation_method='Precision Drip'):
        crop_data = self.crop_baselines.get(crop_name, self.crop_baselines['Maize'])
        base_yield = crop_data['base_yield_per_acre']

        soil_mult = self.soil_multipliers.get(soil_type, 1.0)
        irrig_mult = self.irrigation_multipliers.get(irrigation_method, 1.15)

        # Fertilizer response curve (diminishing returns)
        opt_fert = crop_data['optimal_fert']
        fert_ratio = fertilizer_kg_per_acre / opt_fert if opt_fert > 0 else 1.0
        if fert_ratio <= 1.0:
            fert_mult = 0.7 + 0.35 * fert_ratio
        else:
            # Over-fertilization penalty
            fert_mult = 1.05 - 0.1 * min(1.0, fert_ratio - 1.0)

        # Rainfall response curve
        opt_rain = crop_data['optimal_rain']
        rain_diff = abs(rainfall_mm - opt_rain)
        rain_mult = max(0.70, 1.05 - (rain_diff / (opt_rain * 2.5)))

        # Final calculated yield per acre in metric tons
        yield_per_acre = base_yield * soil_mult * irrig_mult * fert_mult * rain_mult
        yield_per_acre = round(max(0.2, yield_per_acre), 2)
        total_yield_tons = round(yield_per_acre * area_acres, 2)

        # Financial estimate
        price_ton = crop_data['price_per_ton']
        estimated_revenue = round(total_yield_tons * price_ton, 2)

        # Comparison vs national regional baseline
        benchmark_tons = round(base_yield * area_acres, 2)
        gain_pct = round(((total_yield_tons - benchmark_tons) / benchmark_tons) * 100, 1)

        # SDG 2 & Sustainability Impact
        calories_per_ton = 3200000 if crop_name in ['Rice', 'Maize', 'Wheat'] else 1800000
        total_calories = total_yield_tons * calories_per_ton
        people_fed_per_year = int(total_calories / (2200 * 365))

        return {
            'crop_name': crop_name,
            'area_acres': area_acres,
            'predicted_yield_per_acre_tons': yield_per_acre,
            'total_predicted_yield_tons': total_yield_tons,
            'benchmark_yield_tons': benchmark_tons,
            'yield_gain_percentage': gain_pct,
            'estimated_gross_revenue_usd': estimated_revenue,
            'price_per_ton_usd': price_ton,
            'sdg2_food_security_impact': f"Yield feeds ~{people_fed_per_year:,} people's annual caloric needs",
            'carbon_efficiency_score': f"{min(98, round(85 + gain_pct * 0.4, 1))}% carbon-to-biomass efficiency",
            'recommendations': [
                f"Adopting {irrigation_method} enhances water productivity by {round((irrig_mult - 0.9) * 100)}% over traditional flooding.",
                f"Nutrient balance index: {round(fert_ratio * 100)}% of agronomic optimum. Avoid nitrogen runoff into local groundwater.",
                f"Soil health multiplier on {soil_type}: {round(soil_mult, 2)}x yield factor."
            ]
        }

yield_model = CropYieldPredictionModel()
