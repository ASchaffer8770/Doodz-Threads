package com.doodzthreads.app.service;

import com.doodzthreads.app.controller.dto.SignupRequest;
import com.doodzthreads.app.domain.User;
import com.doodzthreads.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupRequest req) {
        String email = req.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }

        User u = new User();
        u.setFullName(req.getFullName().trim());
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(req.getPassword())); // BCrypt hash
        u.setRole("USER");
        u.setEnabled(true);

        userRepository.save(u);
    }
}
