package com.example.beat_api_sileo.domain.Key;

public record KeyRequestDTO(String name) {

    public static KeyRequestDTO create(String name) {
        return new KeyRequestDTO(name);
    }

}
