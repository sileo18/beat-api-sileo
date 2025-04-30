package com.example.beat_api_sileo.domain.Genre;

public record GenreResponseDTO(Long id, String name) {

    public static GenreResponseDTO create(Long id, String name) {
        return new GenreResponseDTO(id, name);
    }
}
