package com.example.beat_api_sileo.domain.Api;

import lombok.Builder;

import java.util.UUID;

@Builder
public record LoginResponseDTO(String token, String surname) {

}