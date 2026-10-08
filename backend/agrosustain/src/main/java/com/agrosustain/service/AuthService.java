package com.agrosustain.service;

import com.agrosustain.dto.AuthRequest;
import com.agrosustain.dto.AuthResponse;
import com.agrosustain.dto.RegisterRequest;
import com.agrosustain.model.User;
import com.agrosustain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return AuthResponse.failure("An account with email " + request.getEmail() + " already exists.");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail().toLowerCase().trim());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setLocation(request.getLocation() != null ? request.getLocation() : "Agricultural District");
        user.setFarmSizeAcres(request.getFarmSizeAcres() != null ? request.getFarmSizeAcres() : 5.0);
        user.setPrimarySoilType(request.getPrimarySoilType() != null ? request.getPrimarySoilType() : "Loamy");

        User saved = userRepository.save(user);
        String token = "AGRO-JWT-" + UUID.randomUUID().toString();

        return new AuthResponse(
                true,
                "Registration successful. Welcome to AgroSustain!",
                token,
                saved.getId(),
                saved.getFullName(),
                saved.getEmail(),
                saved.getLocation(),
                saved.getFarmSizeAcres(),
                saved.getPrimarySoilType()
        );
    }

    public AuthResponse login(AuthRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail().toLowerCase().trim());
        if (userOpt.isEmpty()) {
            // Check default demo account fallback
            if ("farmer@agrosustain.org".equalsIgnoreCase(request.getEmail()) && "farmer123".equals(request.getPassword())) {
                User demo = new User("Green Valley Farmer", "farmer@agrosustain.org", "+1 555 019 2834", passwordEncoder.encode("farmer123"), "Midwest Ag Zone");
                demo.setFarmSizeAcres(15.5);
                demo.setPrimarySoilType("Alluvial / Loam");
                demo = userRepository.save(demo);
                return new AuthResponse(true, "Login successful", "AGRO-JWT-DEMO-TOKEN", demo.getId(), demo.getFullName(), demo.getEmail(), demo.getLocation(), demo.getFarmSizeAcres(), demo.getPrimarySoilType());
            }
            return AuthResponse.failure("Invalid email or password.");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return AuthResponse.failure("Invalid email or password.");
        }

        String token = "AGRO-JWT-" + UUID.randomUUID().toString();
        return new AuthResponse(
                true,
                "Login successful. Welcome back, " + user.getFullName() + "!",
                token,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getLocation(),
                user.getFarmSizeAcres(),
                user.getPrimarySoilType()
        );
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
