/**
 * AgroSustain - Farmer Dashboard Script
 * Integrates Chart.js for 4 visualizations and live REST API telemetry
 */

let waterChartInstance = null;
let yieldChartInstance = null;
let resourceChartInstance = null;
let sdgRadarInstance = null;

document.addEventListener('DOMContentLoaded', async () => {
    const user = Auth.getUser();
    
    // Update profile info
    const farmerNameEl = document.getElementById('dashFarmerName');
    const farmLocationEl = document.getElementById('dashFarmLocation');
    const farmSizeEl = document.getElementById('dashFarmSize');
    
    if (farmerNameEl) farmerNameEl.textContent = user.fullName;
    if (farmLocationEl) farmLocationEl.textContent = user.location || 'Midwest Ag Zone';
    if (farmSizeEl) farmSizeEl.textContent = (user.farmSizeAcres || 15.5) + ' Acres';

    await loadDashboardTelemetry(user.userId);
});

async function loadDashboardTelemetry(userId) {
    let data = null;
    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}/dashboard/summary?userId=${userId || 1}`);
        if (res.ok) {
            data = await res.json();
        }
    } catch (e) {
        console.warn('Dashboard REST API offline, loading built-in state:', e);
    }

    if (!data) {
        // Fallback data
        data = {
            recommendedCrop: 'Rice (Paddy)',
            cropSuitability: 94.5,
            currentWeather: 'Partly Cloudy, 24.8°C',
            currentTemperature: 24.8,
            irrigationStatus: 'Optimal (Drip active)',
            soilMoisture: 34.0,
            diseaseStatus: 'Healthy - Low Pathogen Pressure',
            expectedYieldTons: 42.5,
            sustainabilityScore: 91.8,
            waterUsageChart: {
                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                smartDripUsage: [18200, 16400, 0, 14200, 15800, 0, 19100],
                conventionalFloodUsage: [32000, 31000, 29000, 33000, 31500, 28000, 34000]
            },
            cropYieldChart: {
                labels: ['Rice', 'Maize', 'Wheat', 'Chickpea', 'Tomato'],
                predictedTonsPerAcre: [4.25, 4.50, 3.40, 1.85, 15.20],
                regionalBenchmarkTonsPerAcre: [3.60, 3.80, 2.90, 1.40, 12.00]
            },
            resourceDistributionChart: {
                labels: ['Bio-Fertilizer', 'Precision Irrigation', 'Renewable Solar Pumping', 'IPM Biological Control', 'Recycled Biomass Mulch'],
                percentages: [28, 32, 18, 12, 10]
            },
            sdgRadarChart: {
                labels: ['SDG 2: Zero Hunger', 'SDG 6: Clean Water', 'SDG 12: Responsible Inputs', 'SDG 13: Climate Action', 'SDG 15: Life on Land'],
                scores: [92.4, 95.0, 88.5, 90.5, 94.0]
            }
        };
    }

    // Populate KPI Cards
    const kpiCrop = document.getElementById('kpiCrop');
    const kpiWeather = document.getElementById('kpiWeather');
    const kpiIrrigation = document.getElementById('kpiIrrigation');
    const kpiDisease = document.getElementById('kpiDisease');
    const kpiYield = document.getElementById('kpiYield');
    const kpiSdg = document.getElementById('kpiSdg');

    if (kpiCrop) kpiCrop.textContent = data.recommendedCrop;
    if (kpiWeather) kpiWeather.textContent = data.currentWeather;
    if (kpiIrrigation) kpiIrrigation.textContent = data.irrigationStatus;
    if (kpiDisease) kpiDisease.textContent = data.diseaseStatus;
    if (kpiYield) kpiYield.textContent = data.expectedYieldTons + ' Tons';
    if (kpiSdg) kpiSdg.textContent = data.sustainabilityScore + ' / 100';

    initCharts(data);
}

function initCharts(data) {
    if (typeof Chart === 'undefined') return;

    // --- Chart 1: Water Usage Line Chart ---
    const waterCtx = document.getElementById('waterUsageChart')?.getContext('2d');
    if (waterCtx) {
        if (waterChartInstance) waterChartInstance.destroy();
        waterChartInstance = new Chart(waterCtx, {
            type: 'line',
            data: {
                labels: data.waterUsageChart.labels,
                datasets: [
                    {
                        label: 'Smart Drip (Liters)',
                        data: data.waterUsageChart.smartDripUsage,
                        borderColor: '#10b981',
                        backgroundColor: 'rgba(16, 185, 129, 0.15)',
                        fill: true,
                        tension: 0.35,
                        borderWidth: 2.5
                    },
                    {
                        label: 'Conventional Flood Baseline',
                        data: data.waterUsageChart.conventionalFloodUsage,
                        borderColor: '#94a3b8',
                        borderDash: [5, 5],
                        fill: false,
                        tension: 0.3,
                        borderWidth: 1.5
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { position: 'bottom' }
                },
                scales: {
                    y: { beginAtZero: true, title: { display: true, text: 'Volume (Liters)' } }
                }
            }
        });
    }

    // --- Chart 2: Crop Yield Comparison Bar Chart ---
    const yieldCtx = document.getElementById('cropYieldChart')?.getContext('2d');
    if (yieldCtx) {
        if (yieldChartInstance) yieldChartInstance.destroy();
        yieldChartInstance = new Chart(yieldCtx, {
            type: 'bar',
            data: {
                labels: data.cropYieldChart.labels,
                datasets: [
                    {
                        label: 'AgroSustain AI Predicted (Tons/Acre)',
                        data: data.cropYieldChart.predictedTonsPerAcre,
                        backgroundColor: '#059669',
                        borderRadius: 6
                    },
                    {
                        label: 'Regional Benchmark Average',
                        data: data.cropYieldChart.regionalBenchmarkTonsPerAcre,
                        backgroundColor: '#cbd5e1',
                        borderRadius: 6
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: { legend: { position: 'bottom' } },
                scales: { y: { beginAtZero: true } }
            }
        });
    }

    // --- Chart 3: Resource Distribution Doughnut ---
    const resourceCtx = document.getElementById('resourceChart')?.getContext('2d');
    if (resourceCtx) {
        if (resourceChartInstance) resourceChartInstance.destroy();
        resourceChartInstance = new Chart(resourceCtx, {
            type: 'doughnut',
            data: {
                labels: data.resourceDistributionChart.labels,
                datasets: [{
                    data: data.resourceDistributionChart.percentages,
                    backgroundColor: ['#10b981', '#0ea5e9', '#f59e0b', '#8b5cf6', '#14b8a6'],
                    borderWidth: 0
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: { legend: { position: 'right' } }
            }
        });
    }

    // --- Chart 4: SDG 5-Pillar Radar Chart ---
    const sdgCtx = document.getElementById('sdgRadarChart')?.getContext('2d');
    if (sdgCtx) {
        if (sdgRadarInstance) sdgRadarInstance.destroy();
        sdgRadarInstance = new Chart(sdgCtx, {
            type: 'radar',
            data: {
                labels: data.sdgRadarChart.labels,
                datasets: [{
                    label: 'Farm SDG Score (%)',
                    data: data.sdgRadarChart.scores,
                    backgroundColor: 'rgba(16, 185, 129, 0.25)',
                    borderColor: '#10b981',
                    pointBackgroundColor: '#059669',
                    pointBorderColor: '#fff',
                    pointHoverBackgroundColor: '#fff',
                    pointHoverBorderColor: '#059669',
                    borderWidth: 2
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    r: {
                        angleLines: { color: 'rgba(0,0,0,0.08)' },
                        suggestedMin: 50,
                        suggestedMax: 100
                    }
                },
                plugins: { legend: { display: false } }
            }
        });
    }
}
