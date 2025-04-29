package com.example.beat_api_sileo.domain.Beat;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record CreateBeatRequestDTO(String name,
                                   int BPM,
                                   Key key,
                                   MultipartFile image,
                                   MultipartFile audio,
                                   String genre,
                                   UUID userId) {

    public static CreateBeatRequestDTO create(String name,
                                   int BPM,
                                   Key key,
                                   MultipartFile imageUrl,
                                   MultipartFile audioUrl,
                                   String genre,
                                   UUID userId) {
        return new CreateBeatRequestDTO(name, BPM, key, imageUrl, audioUrl, genre, userId);
    }

}
