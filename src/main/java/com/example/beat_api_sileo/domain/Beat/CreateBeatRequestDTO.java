package com.example.beat_api_sileo.domain.Beat;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record CreateBeatRequestDTO(String name,
                                   int bpm,
                                   Key key,
                                   MultipartFile image,
                                   MultipartFile audio,
                                   Genre genre,
                                   UUID userId) {

    public static CreateBeatRequestDTO create(String name,
                                   int bpm,
                                   Key key,
                                   MultipartFile imageUrl,
                                   MultipartFile audioUrl,
                                              Genre genre,
                                   UUID userId) {
        return new CreateBeatRequestDTO(name, bpm, key, imageUrl, audioUrl, genre, userId);
    }

}
