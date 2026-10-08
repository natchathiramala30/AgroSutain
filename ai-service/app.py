"""
AgroSustain - AI/ML Microservice (Flask REST API)
Exposes endpoints for:
1. Crop Recommendation: POST /predict/crop
2. Plant Disease Detection: POST /predict/disease
3. Crop Yield Prediction: POST /predict/yield
4. Smart Irrigation Guidance: POST /predict/irrigation
5. Health Check & Catalog: GET /health, GET /catalog
"""

from flask import Flask, request, jsonify
from flask_cors import CORS
import base64
import os
import traceback

from crop_model import crop_model
from disease_model import disease_model
from yield_model import yield_model
from irrigation_model import irrigation_model

app = Flask(__name__)
CORS(app)

@app.route('/health', methods=['GET'])
def health_check():
    return jsonify({
        'status': 'HEALTHY',
        'service': 'AgroSustain AI/ML Engine',
        'version': '1.0.0',
        'endpoints': [
            '/predict/crop',
            '/predict/disease',
            '/predict/yield',
            '/predict/irrigation',
            '/catalog'
        ],
        'sdg_pillars': ['SDG 2', 'SDG 6', 'SDG 12', 'SDG 13', 'SDG 15']
    }), 200

@app.route('/catalog', methods=['GET'])
def get_catalog():
    return jsonify({
        'supported_crops': list(crop_model.crops_profile.keys()),
        'supported_soils': list(irrigation_model.soil_properties.keys()),
        'supported_irrigation_systems': list(irrigation_model.systems.keys()),
        'disease_pathology_catalog_count': len(disease_model.disease_catalog)
    }), 200

@app.route('/predict/crop', methods=['POST'])
def predict_crop():
    try:
        data = request.get_json(force=True)
        if not data:
            return jsonify({'error': 'JSON payload is required'}), 400

        n = float(data.get('nitrogen', data.get('n', 60.0)))
        p = float(data.get('phosphorus', data.get('p', 45.0)))
        k = float(data.get('potassium', data.get('k', 40.0)))
        temp = float(data.get('temperature', 25.0))
        hum = float(data.get('humidity', 70.0))
        ph = float(data.get('soil_ph', data.get('ph', 6.5)))
        rain = float(data.get('rainfall', 120.0))

        result = crop_model.predict(n, p, k, temp, hum, ph, rain)
        return jsonify({
            'success': True,
            'data': result
        }), 200
    except Exception as e:
        traceback.print_exc()
        return jsonify({'success': False, 'error': str(e)}), 500

@app.route('/predict/disease', methods=['POST'])
def predict_disease():
    try:
        crop_hint = request.form.get('crop', request.args.get('crop', ''))
        image_bytes = None
        filename = "uploaded_leaf.jpg"

        # Check multipart form file
        if 'image' in request.files:
            file = request.files['image']
            image_bytes = file.read()
            filename = file.filename
        elif request.is_json:
            data = request.get_json()
            if 'image_base64' in data:
                b64 = data['image_base64']
                if ',' in b64:
                    b64 = b64.split(',')[1]
                image_bytes = base64.b64decode(b64)
            if 'crop' in data:
                crop_hint = data['crop']

        result = disease_model.predict(image_bytes=image_bytes, crop_hint=crop_hint, filename=filename)
        return jsonify({
            'success': True,
            'data': result
        }), 200
    except Exception as e:
        traceback.print_exc()
        return jsonify({'success': False, 'error': str(e)}), 500

@app.route('/predict/yield', methods=['POST'])
def predict_yield():
    try:
        data = request.get_json(force=True)
        if not data:
            return jsonify({'error': 'JSON payload is required'}), 400

        crop_name = data.get('crop_name', data.get('crop', 'Maize'))
        area_acres = float(data.get('area_acres', data.get('area', 5.0)))
        soil_type = data.get('soil_type', 'Loamy')
        fertilizer_kg = float(data.get('fertilizer_usage_kg', data.get('fertilizer', 140.0)))
        rainfall_mm = float(data.get('rainfall_mm', data.get('rainfall', 120.0)))
        irrigation_method = data.get('irrigation_method', 'Precision Drip')

        result = yield_model.predict(crop_name, area_acres, soil_type, fertilizer_kg, rainfall_mm, irrigation_method)
        return jsonify({
            'success': True,
            'data': result
        }), 200
    except Exception as e:
        traceback.print_exc()
        return jsonify({'success': False, 'error': str(e)}), 500

@app.route('/predict/irrigation', methods=['POST'])
def predict_irrigation():
    try:
        data = request.get_json(force=True)
        if not data:
            return jsonify({'error': 'JSON payload is required'}), 400

        crop_type = data.get('crop_type', data.get('crop', 'Maize'))
        soil_type = data.get('soil_type', 'Loamy')
        current_moisture = float(data.get('soil_moisture_pct', data.get('moisture', 32.0)))
        area_acres = float(data.get('area_acres', data.get('area', 5.0)))
        temperature = float(data.get('temperature', 28.0))
        humidity = float(data.get('humidity', 60.0))
        forecast_rain = float(data.get('forecast_rain_mm', 0.0))
        irrigation_method = data.get('irrigation_method', 'Precision Drip')

        result = irrigation_model.calculate(
            crop_type=crop_type,
            soil_type=soil_type,
            current_moisture_pct=current_moisture,
            area_acres=area_acres,
            temperature=temperature,
            humidity=humidity,
            forecast_rain_mm=forecast_rain,
            irrigation_method=irrigation_method
        )
        return jsonify({
            'success': True,
            'data': result
        }), 200
    except Exception as e:
        traceback.print_exc()
        return jsonify({'success': False, 'error': str(e)}), 500

if __name__ == '__main__':
    port = int(os.environ.get('PORT', 5000))
    print(f"[AgroSustain AI/ML Engine] starting on port {port}...")
    app.run(host='0.0.0.0', port=port, debug=False)
