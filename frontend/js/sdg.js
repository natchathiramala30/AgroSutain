/**
 * AgroSustain - SDG Sustainability & Impact Tracking Script
 */

document.addEventListener('DOMContentLoaded', async () => {
    await loadSdgImpactMetrics();
});

async function loadSdgImpactMetrics() {
    const user = Auth.getUser();
    let data = null;

    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}/sdg/impact-summary?userId=${user.userId || 1}`);
        if (res.ok) {
            data = await res.json();
        }
    } catch (e) {
        console.warn('SDG REST API offline, loading default benchmark:', e);
    }

    if (!data) {
        data = {
            overallScore: 91.8,
            sustainabilityGrade: 'A+ Eco-Champion',
            sdg2ZeroHungerScore: 92.4,
            sdg6CleanWaterScore: 95.0,
            sdg12ResponsibleConsumptionScore: 88.5,
            sdg13ClimateActionScore: 90.5,
            sdg15LifeOnLandScore: 94.0,
            totalWaterSavedLiters: 142500,
            carbonOffsetKg: 840,
            chemicalReductionPct: 28.5,
            badgesUnlocked: [
                { title: 'Aquifer Guardian', sdg: 'SDG 6', desc: 'Saved >100,000 Liters of groundwater with Drip Scheduling', icon: 'fa-droplet' },
                { title: 'Zero Runoff Master', sdg: 'SDG 12', desc: 'Reduced excess synthetic nitrogen by >25%', icon: 'fa-seedling' },
                { title: 'Carbon Sink Pioneer', sdg: 'SDG 13', desc: 'Offset 800+ kg CO2 via bio-mulching and legume rotation', icon: 'fa-cloud-sun' },
                { title: 'Soil Microbiome Protector', sdg: 'SDG 15', desc: 'Maintained >90% soil biological health index', icon: 'fa-earth-americas' }
            ],
            sdgActionRecommendations: [
                'SDG 2: Plant leguminous pulse cover crops between main crop rows to naturally enhance biological nitrogen fixation.',
                'SDG 6: Calibrate drip emitters before peak summer heat to maintain 92%+ water application efficiency.',
                'SDG 12: Transition 20% of synthetic phosphorus input to mycorrhizal bio-fertilizers.',
                'SDG 13: Adopt zero-tillage relay sowing to lock 120 kg/acre additional organic carbon in the soil.',
                'SDG 15: Establish flowering border plants (marigold/mustard) to nurture natural pollinator populations.'
            ]
        };
    }

    renderSdgScorecard(data);
}

function renderSdgScorecard(d) {
    const overallScoreEl = document.getElementById('sdgOverallScore');
    const gradeEl = document.getElementById('sdgGrade');
    const waterSavedEl = document.getElementById('sdgWaterSaved');
    const co2OffsetEl = document.getElementById('sdgCo2Offset');
    const chemReducedEl = document.getElementById('sdgChemReduced');
    const badgesContainer = document.getElementById('sdgBadgesGrid');
    const actionList = document.getElementById('sdgActionList');

    if (overallScoreEl) overallScoreEl.textContent = `${d.overallScore}%`;
    if (gradeEl) gradeEl.textContent = d.sustainabilityGrade;
    if (waterSavedEl) waterSavedEl.textContent = `${Number(d.totalWaterSavedLiters).toLocaleString()} L`;
    if (co2OffsetEl) co2OffsetEl.textContent = `${d.carbonOffsetKg} kg`;
    if (chemReducedEl) chemReducedEl.textContent = `${d.chemicalReductionPct}%`;

    // Render 5 Pillar Bars
    setProgressBar('bar_sdg2', d.sdg2ZeroHungerScore);
    setProgressBar('bar_sdg6', d.sdg6CleanWaterScore);
    setProgressBar('bar_sdg12', d.sdg12ResponsibleConsumptionScore);
    setProgressBar('bar_sdg13', d.sdg13ClimateActionScore);
    setProgressBar('bar_sdg15', d.sdg15LifeOnLandScore);

    // Badges
    if (badgesContainer && d.badgesUnlocked) {
        badgesContainer.innerHTML = d.badgesUnlocked.map(b => `
            <div style="background: white; border-radius: var(--radius-md); padding: 1.25rem; border: 1px solid rgba(16, 185, 129, 0.15); display: flex; align-items: center; gap: 1rem; box-shadow: var(--shadow-sm);">
                <div style="width: 48px; height: 48px; border-radius: 50%; background: rgba(16, 185, 129, 0.15); color: #059669; display: flex; align-items: center; justify-content: center; font-size: 1.3rem;">
                    <i class="fa-solid ${b.icon}"></i>
                </div>
                <div>
                    <span style="font-size: 0.72rem; text-transform: uppercase; color: #10b981; font-weight: 700;">${b.sdg}</span>
                    <h5 style="font-size: 1rem; color: var(--forest-dark); margin: 2px 0;">${b.title}</h5>
                    <p style="font-size: 0.8rem; color: var(--text-muted);">${b.desc}</p>
                </div>
            </div>
        `).join('');
    }

    // Action recommendations
    if (actionList && d.sdgActionRecommendations) {
        actionList.innerHTML = d.sdgActionRecommendations.map(act => `
            <li style="margin-bottom: 0.75rem; display: flex; align-items: flex-start; gap: 0.6rem; color: #334155; font-size: 0.9rem;">
                <i class="fa-solid fa-leaf text-success" style="margin-top: 3px;"></i>
                <span>${act}</span>
            </li>
        `).join('');
    }
}

function setProgressBar(elementId, value) {
    const el = document.getElementById(elementId);
    const label = document.getElementById(`${elementId}_val`);
    if (el) el.style.width = `${value}%`;
    if (label) label.textContent = `${Math.round(value)}%`;
}
