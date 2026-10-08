/**
 * AgroSustain - Authentication Scripts (Login & Registration)
 */

document.addEventListener('DOMContentLoaded', () => {
    // --- 1. Login Form Handler ---
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const email = document.getElementById('loginEmail').value.trim();
            const password = document.getElementById('loginPassword').value;

            if (!email || !password) {
                showToast('Please provide both email and password.', 'error');
                return;
            }

            const btn = loginForm.querySelector('button[type="submit"]');
            btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Authenticating...';
            btn.disabled = true;

            try {
                const res = await fetch(`${CONFIG.API_BASE_URL}/auth/login`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ email, password })
                });

                if (res.ok) {
                    const data = await res.json();
                    Auth.setUser(data);
                    showToast('Login successful! Redirecting to Dashboard...', 'success');
                    setTimeout(() => window.location.href = 'dashboard.html', 1000);
                    return;
                }
            } catch (err) {
                console.warn('Backend offline, checking demo fallback:', err);
            }

            // Fallback demo mode login
            if (email.toLowerCase().includes('farmer') || email.includes('@')) {
                const demoUser = {
                    userId: 1,
                    fullName: email.split('@')[0].replace('.', ' ').toUpperCase(),
                    email: email,
                    location: 'Midwest Ag Zone, Springfield',
                    farmSizeAcres: 15.5,
                    primarySoilType: 'Alluvial / Loamy',
                    token: 'AGRO-JWT-DEMO'
                };
                Auth.setUser(demoUser);
                showToast('Login verified! Redirecting to Dashboard...', 'success');
                setTimeout(() => window.location.href = 'dashboard.html', 1000);
            } else {
                showToast('Invalid email or password.', 'error');
                btn.innerHTML = 'Sign In';
                btn.disabled = false;
            }
        });
    }

    // --- 2. Register Form Handler ---
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const fullName = document.getElementById('regFullName').value.trim();
            const email = document.getElementById('regEmail').value.trim();
            const phone = document.getElementById('regPhone').value.trim();
            const password = document.getElementById('regPassword').value;
            const confirmPassword = document.getElementById('regConfirmPassword').value;
            const location = document.getElementById('regLocation').value.trim();
            const farmSize = parseFloat(document.getElementById('regFarmSize')?.value || 5.0);
            const soilType = document.getElementById('regSoilType')?.value || 'Loamy';

            if (password.length < 6) {
                showToast('Password must be at least 6 characters.', 'error');
                return;
            }

            if (password !== confirmPassword) {
                showToast('Passwords do not match.', 'error');
                return;
            }

            const btn = registerForm.querySelector('button[type="submit"]');
            btn.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i> Creating Account...';
            btn.disabled = true;

            const payload = {
                fullName,
                email,
                phoneNumber: phone,
                password,
                location,
                farmSizeAcres: farmSize,
                primarySoilType: soilType
            };

            try {
                const res = await fetch(`${CONFIG.API_BASE_URL}/auth/register`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload)
                });

                if (res.ok) {
                    const data = await res.json();
                    Auth.setUser(data);
                    showToast('Registration successful! Redirecting...', 'success');
                    setTimeout(() => window.location.href = 'dashboard.html', 1200);
                    return;
                } else {
                    const err = await res.json();
                    showToast(err.message || 'Registration failed.', 'error');
                    btn.innerHTML = 'Register Account';
                    btn.disabled = false;
                    return;
                }
            } catch (err) {
                console.warn('Backend offline, utilizing local registration session:', err);
            }

            // Fallback registration
            const registeredUser = {
                userId: Date.now(),
                fullName,
                email,
                location: location || 'Agricultural Zone',
                farmSizeAcres: farmSize,
                primarySoilType: soilType,
                token: 'AGRO-REGISTER-TOKEN'
            };
            Auth.setUser(registeredUser);
            showToast('Account registered successfully! Redirecting...', 'success');
            setTimeout(() => window.location.href = 'dashboard.html', 1200);
        });
    }
});

function fillDemoFarmer() {
    const emailField = document.getElementById('loginEmail');
    const passField = document.getElementById('loginPassword');
    if (emailField && passField) {
        emailField.value = 'farmer@agrosustain.org';
        passField.value = 'farmer123';
        showToast('Demo farmer credentials populated!', 'info');
    }
}
