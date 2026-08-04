package com.example.website.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Username cannot be empty")
        @Size(min = 4, max = 25, message = "Username must be between 4 and 25 characters long.")
        String name,

        @NotBlank(message = "Email address cannot be empty")
        @Email(message = "Please provide a valid email address")
        String email,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters long.")
        String password
){}
