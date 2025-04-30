package com.example.beat_api_sileo.mapper;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Genre.GenreRequestDTO;
import com.example.beat_api_sileo.domain.Genre.GenreResponseDTO;
import com.example.beat_api_sileo.domain.Key.Key;
import com.example.beat_api_sileo.domain.Key.KeyRequestDTO;
import com.example.beat_api_sileo.domain.Key.KeyResponseDTO;

public class KeyMapper {

    public static Key toKey(KeyRequestDTO request) {
        return new Key(

                request.name()
        );
    }

    public static KeyResponseDTO toKeyReponse(Key response) {
        return KeyResponseDTO.create(
                response.getId(),
                response.getName()
        );
    }
}
