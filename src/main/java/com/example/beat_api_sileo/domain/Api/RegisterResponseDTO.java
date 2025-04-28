package com.example.beat_api_sileo.domain.Api;

import com.example.beat_api_sileo.domain.User.User;
import lombok.Builder;

import java.util.UUID;

public record RegisterResponseDTO(UUID id, String name, String email) {
    public static RegisterResponseDTO create(UUID id, String name, String email) {
        return new RegisterResponseDTO(id, name, email);
    }


}
