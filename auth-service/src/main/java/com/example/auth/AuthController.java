package com.example.auth;

import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignupRequest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest) {
        Map<String, String> token = authService.login(loginRequest);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody SignupRequest signupRequest) {
        String token = authService.signup(signupRequest);
        return ResponseEntity.ok().body(token);
    }

}
