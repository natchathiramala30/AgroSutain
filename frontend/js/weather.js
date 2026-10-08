/**
 * AgroSustain - Agricultural Weather Advisory Script
 */

document.addEventListener('DOMContentLoaded', async () => {
    const locInput = document.getElementById('weatherLocationInput');
    const searchBtn = document.getElementById('btnSearchWeather');

    const defaultLoc = Auth.getUser().location || 'Midwest Agricultural Zone';
    if (locInput) locInput.value = defaultLoc;

    if (searchBtn) {
        searchBtn.addEventListener('click', () => {
            const loc = locInput ? locInput.value : defaultLoc;
            loadWeatherAdvisory(loc);
        });
    }

    await loadWeatherAdvisory(defaultLoc);
});

async function loadWeatherAdvisory(location) {
    let data = null;

    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}/weather/advisory?location=${encodeURIComponent(location)}`);
        if (res.ok) {
            data = await res.json();
        }
    } catch (e) {
        console.warn('Weather API offline, using agro-weather simulation:', e);
    }

    if (!data) {
        data = {
            location: location,
            currentTemperature: 24.8,
            humidity: 68.0,
            windSpeedKmh: 11.4,
            weatherCondition: 'Partly Cloudy with Gentle Breeze',
            icon: 'cloud-sun',
            precipitationProbability: 18.0,
            soilMoistureEstimate: 36.5,
            sprayingSuitable: true,
            harvestingSuitable: true,
            agroAdvisory: 'Optimal weather window for bio-fertilizer foliar application and field weeding. Low fungal disease risk over next 48 hours. Wind speed (<15 km/h) safe for precision spraying.',
            forecast5Days: [
                { date: 'Wed, Oct 7', dayName: 'Wednesday', temperature: 25.2, humidity: 65, condition: 'Partly Cloudy', precipitationProbability: 15, advisory: 'Good day for soil aeration and field preparation.', sprayingSuitable: true },
                { date: 'Thu, Oct 8', dayName: 'Thursday', temperature: 27.0, humidity: 58, condition: 'Clear & Sunny', precipitationProbability: 5, advisory: 'Ideal for harvesting and solar grain drying.', sprayingSuitable: true },
                { date: 'Fri, Oct 9', dayName: 'Friday', temperature: 23.5, humidity: 82, condition: 'Scattered Showers', precipitationProbability: 75, advisory: 'Rain expected. Suspend nitrogen top-dressing.', sprayingSuitable: false },
                { date: 'Sat, Oct 10', dayName: 'Saturday', temperature: 22.8, humidity: 74, condition: 'Overcast', precipitationProbability: 35, advisory: 'Good for seedling transplantation.', sprayingSuitable: true },
                { date: 'Sun, Oct 11', dayName: 'Sunday', temperature: 28.1, humidity: 55, condition: 'Sunny & Warm', precipitationProbability: 10, advisory: 'Warm dry weather. Check drip lines.', sprayingSuitable: true }
            ]
        };
    }

    renderWeatherDetails(data);
}

function renderWeatherDetails(d) {
    const tempEl = document.getElementById('weatherCurrentTemp');
    const condEl = document.getElementById('weatherCurrentCond');
    const humEl = document.getElementById('weatherHumidity');
    const windEl = document.getElementById('weatherWind');
    const rainEl = document.getElementById('weatherRainProb');
    const soilEl = document.getElementById('weatherSoilMoist');
    const advEl = document.getElementById('weatherAdvisoryText');
    const sprayBadge = document.getElementById('weatherSprayBadge');
    const harvestBadge = document.getElementById('weatherHarvestBadge');
    const forecastGrid = document.getElementById('weatherForecastGrid');

    if (tempEl) tempEl.textContent = `${d.currentTemperature}°C`;
    if (condEl) condEl.textContent = d.weatherCondition;
    if (humEl) humEl.textContent = `${d.humidity}%`;
    if (windEl) windEl.textContent = `${d.windSpeedKmh} km/h`;
    if (rainEl) rainEl.textContent = `${d.precipitationProbability}%`;
    if (soilEl) soilEl.textContent = `${d.soilMoistureEstimate || 36}%`;
    if (advEl) advEl.textContent = d.agroAdvisory;

    if (sprayBadge) {
        sprayBadge.innerHTML = d.sprayingSuitable 
            ? '<i class="fa-solid fa-circle-check text-success"></i> Spraying Safe' 
            : '<i class="fa-solid fa-triangle-exclamation text-danger"></i> High Wind / Rain - Avoid Spraying';
        sprayBadge.style.color = d.sprayingSuitable ? '#10b981' : '#ef4444';
    }

    if (harvestBadge) {
        harvestBadge.innerHTML = d.harvestingSuitable 
            ? '<i class="fa-solid fa-circle-check text-success"></i> Harvesting Window Open' 
            : '<i class="fa-solid fa-circle-xmark text-warning"></i> High Moisture - Delay Harvest';
        harvestBadge.style.color = d.harvestingSuitable ? '#10b981' : '#f59e0b';
    }

    if (forecastGrid && d.forecast5Days) {
        forecastGrid.innerHTML = d.forecast5Days.map(f => `
            <div class="forecast-day-card" style="background: white; border: 1px solid rgba(16, 185, 129, 0.15); border-radius: var(--radius-md); padding: 1.25rem; text-align: center; box-shadow: var(--shadow-sm);">
                <span style="font-size: 0.85rem; font-weight: 700; color: var(--forest-dark); display: block; margin-bottom: 0.25rem;">${f.date}</span>
                <span style="font-size: 0.75rem; color: #64748b;">${f.dayName}</span>
                <div style="font-size: 1.75rem; color: var(--primary); margin: 0.75rem 0;"><i class="fa-solid fa-cloud-sun"></i></div>
                <h4 style="font-size: 1.4rem; color: var(--forest-dark); margin-bottom: 0.25rem;">${f.temperature}°C</h4>
                <p style="font-size: 0.8rem; color: var(--text-muted); margin-bottom: 0.75rem;">${f.condition}</p>
                <div style="font-size: 0.78rem; padding: 0.35rem 0.5rem; background: #f1f5f9; border-radius: 6px; color: #334155; margin-bottom: 0.5rem;">
                    <i class="fa-solid fa-droplet text-info"></i> Rain: ${f.precipitationProbability}%
                </div>
                <p style="font-size: 0.75rem; color: #475569; font-style: italic;">${f.advisory}</p>
            </div>
        `).join('');
    }
}
