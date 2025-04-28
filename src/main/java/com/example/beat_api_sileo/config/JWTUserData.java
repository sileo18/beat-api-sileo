package com.example.beat_api_sileo.config;

import lombok.Builder;

public record JWTUserData(String id, String name, String email) {

    public static JWTUserData of(String id, String name, String email) {
        return new JWTUserData(id, name, email);
    }
}
