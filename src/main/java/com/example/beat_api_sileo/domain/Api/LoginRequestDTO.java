package com.example.beat_api_sileo.domain.Api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginRequestDTO(

        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password) {

}
