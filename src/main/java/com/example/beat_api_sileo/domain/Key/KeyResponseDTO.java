package com.example.beat_api_sileo.domain.Key;

public record KeyResponseDTO(Long id, String name) {

    public static KeyResponseDTO create(Long id, String name) {
        return new KeyResponseDTO(id, name);
    }
}

