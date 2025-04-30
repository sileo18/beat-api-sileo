package com.example.beat_api_sileo.mapper;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Genre.GenreRequestDTO;
import com.example.beat_api_sileo.domain.Genre.GenreResponseDTO;

public class GenreMapper {

    public static Genre toGenre(GenreRequestDTO request) {
        return new Genre(

                request.name()
        );
    }

    public static GenreResponseDTO toGenreReponse(Genre response) {
        return GenreResponseDTO.create(
                response.getId(),
                response.getName()
        );
    }
}
