package com.example.beat_api_sileo.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateDescriptionRequestDTO(
        @Size(max = 255, message = "Description must be at most 255 characters")
        String description,
        @Email
        String email) {
}
