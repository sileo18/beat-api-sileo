package com.example.beat_api_sileo.domain.Api;

import java.util.UUID;

public class LoginResponseDTO {
    private String token;
    private String name;
    private UUID id;

    public LoginResponseDTO(String token, String name, UUID id) {
        this.token = token;
        this.name = name;
        this.id = id;
    }

    public String getToken() {
        return token; // Corrigido para ser um método de instância
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id; // Corrigido para ser um método de instância
    }

    public void setId(UUID id) {
        this.id = id;
    }
}