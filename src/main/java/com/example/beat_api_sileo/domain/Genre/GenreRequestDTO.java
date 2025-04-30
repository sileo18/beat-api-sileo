package com.example.beat_api_sileo.domain.Genre;

public record GenreRequestDTO(String name) {

    public static GenreRequestDTO create(String name) {
        return new GenreRequestDTO(name);
    }

}
