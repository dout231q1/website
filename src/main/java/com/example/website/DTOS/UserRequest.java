package com.example.website.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotEmpty(message = "Username cannot be empty")
        @Min(message = "Name must be at least 4 characters long.", value = 4)
        @Max(message = "Name cannot exceed 25 characters.", value = 25)
        String name,
        @NotEmpty(message = "Email address cannot be empty")
        @Email(message = "Please provide a valid email address")
        String email,
        @NotEmpty(message = "Password cannot be empty")
        @Min(message = "Password must be at least 8 characters long.", value = 8)
        @Max(message = "Password cannot exceed 64 characters.", value = 64)
        String password
){}
