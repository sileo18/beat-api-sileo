package com.example.beat_api_sileo.domain.User;

public record UpdateDescriptionResponseDTO(String name, String email, String description) {

    public static UpdateDescriptionResponseDTO create(String name, String email, String description) {
        return new UpdateDescriptionResponseDTO(name, email, description);
    }

}
