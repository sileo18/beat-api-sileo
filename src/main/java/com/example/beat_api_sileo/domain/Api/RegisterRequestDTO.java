package com.example.beat_api_sileo.domain.Api;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record RegisterRequestDTO(String name, String email, String surname, String password, MultipartFile picture) {

    public static RegisterRequestDTO create(String name, String email, String surname, String password, MultipartFile picture) {
        return new RegisterRequestDTO(name, email, surname, password, picture);
    }
}
