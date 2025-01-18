package com.example.question.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.question.dto.LoginRequest;
import com.example.question.dto.SignupRequest;
import com.example.question.model.User;
import com.example.question.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final String secretKey = "web-2024-springboot";

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByPhone(loginRequest.getPhone());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid phone");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid phone or password");
        }

        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("id", user.getId())
                .withClaim("type", user.getType().toString())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(18000)))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String signup(SignupRequest signupRequest) {
        if (userRepository.findByPhone(signupRequest.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone number is already registered");
        }

        User user = new User();
        user.setFirstName(signupRequest.getFirst_name());
        user.setLastName(signupRequest.getLast_name());
        user.setPhone(signupRequest.getPhone());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setType(signupRequest.getType());

        userRepository.save(user);

        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("phone", user.getPhone())
                .withClaim("type", user.getType().toString())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
                .sign(Algorithm.HMAC256(secretKey));
    }
    
}
