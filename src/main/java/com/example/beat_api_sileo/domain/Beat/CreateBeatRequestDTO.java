package com.example.beat_api_sileo.domain.Beat;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Key.Key;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public record CreateBeatRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Min(value = 1, message = "BPM must be greater than 0")
        int bpm,

        @NotNull(message = "Key cannot be null")
        Long keyId,

        @NotNull(message = "Genre ID cannot be null")
        List<Long> genresId,

        @NotNull(message = "Image file is required")
        MultipartFile image,

        @NotNull(message = "Audio file is required")
        MultipartFile audio,

        @NotNull(message = "User ID cannot be null")
        UUID userId
) {
    public static CreateBeatRequestDTO create(String name,
                                              int bpm,
                                              Long keyId,
                                              List<Long> genresId,
                                              MultipartFile imageUrl,
                                              MultipartFile audioUrl,
                                              UUID userId
                                              ) {
        return new CreateBeatRequestDTO(
                name,
                bpm,
                keyId,
                genresId,
                imageUrl,
                audioUrl,
                userId
        );
    }
}