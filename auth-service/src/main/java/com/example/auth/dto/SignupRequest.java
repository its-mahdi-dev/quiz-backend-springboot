package com.example.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    private String phone;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String type;
}