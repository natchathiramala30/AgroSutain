/**
 * AgroSustain - Core Configuration & Global Utilities
 * Automatically connects to Spring Boot REST API on port 8080
 * Features graceful client-side fallback if backend is offline.
 */

const CONFIG = {
    API_BASE_URL: 'http://localhost:8080/api',
    AI_BASE_URL: 'http://localhost:5000',
    IS_MOCK_FALLBACK_ENABLED: true
};

// Global User Session Management
const Auth = {
    getUser() {
        const u = localStorage.getItem('agrosustain_user');
        return u ? JSON.parse(u) : {
            userId: 1,
            fullName: 'Green Valley Farmer',
            email: 'farmer@agrosustain.org',
            location: 'Midwest Agricultural Zone',
            farmSizeAcres: 15.5,
            primarySoilType: 'Alluvial / Loam',
            token: 'AGRO-DEMO-TOKEN'
        };
    },
    setUser(user) {
        localStorage.setItem('agrosustain_user', JSON.stringify(user));
    },
    isLoggedIn() {
        return localStorage.getItem('agrosustain_user') !== null;
    },
    logout() {
        localStorage.removeItem('agrosustain_user');
        window.location.href = 'login.html';
    }
};

// Global Toast Notifications
function showToast(message, type = 'success') {
    let container = document.querySelector('.toast-container');
    if (!container) {
        container = document.createElement('div');
        container.className = 'toast-container';
        document.body.appendChild(container);
    }

    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    
    let icon = 'fa-circle-check';
    if (type === 'error') icon = 'fa-circle-exclamation';
    if (type === 'info') icon = 'fa-circle-info';

    toast.innerHTML = `
        <i class="fa-solid ${icon} fa-lg" style="color: ${type === 'success' ? '#10b981' : type === 'error' ? '#ef4444' : '#0ea5e9'}"></i>
        <div style="flex-grow:1; font-size:0.9rem; font-weight:500;">${message}</div>
        <button onclick="this.parentElement.remove()" style="background:none; border:none; color:#94a3b8; cursor:pointer;"><i class="fa-solid fa-xmark"></i></button>
    `;

    container.appendChild(toast);
    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateX(100%)';
        toast.style.transition = 'all 0.3s ease';
        setTimeout(() => toast.remove(), 300);
    }, 4500);
}

// Mobile Navbar Toggle
document.addEventListener('DOMContentLoaded', () => {
    const toggle = document.querySelector('.mobile-toggle');
    const navLinks = document.querySelector('.nav-links');
    if (toggle && navLinks) {
        toggle.addEventListener('click', () => {
            navLinks.classList.toggle('active');
        });
    }

    // Update user display if present
    const userElements = document.querySelectorAll('.current-user-name');
    const user = Auth.getUser();
    userElements.forEach(el => el.textContent = user.fullName);
});
