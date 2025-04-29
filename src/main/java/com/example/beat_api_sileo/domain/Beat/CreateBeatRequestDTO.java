package com.example.beat_api_sileo.domain.Beat;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record CreateBeatRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Min(value = 1, message = "BPM must be greater than 0")
        int bpm,

        @NotNull(message = "Key cannot be null")
        Key key,

        @NotNull(message = "Image file is required")
        MultipartFile image,

        @NotNull(message = "Audio file is required")
        MultipartFile audio,

        @NotNull(message = "Genre cannot be null")
        Genre genre,

        @NotNull(message = "User ID cannot be null")
        UUID userId
) {
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