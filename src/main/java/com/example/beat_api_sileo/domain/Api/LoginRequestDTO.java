package com.example.beat_api_sileo.domain.Api;

import lombok.Builder;

@Builder
public record LoginRequestDTO(String email, String password) {

}
