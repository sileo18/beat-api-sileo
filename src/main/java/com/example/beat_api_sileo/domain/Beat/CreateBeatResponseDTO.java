package com.example.beat_api_sileo.domain.Beat;

import java.util.UUID;

public record CreateBeatResponseDTO(UUID id, String urlAudio, String name) {

    public static CreateBeatResponseDTO create(UUID id, String urlAudio, String name) {
        return new CreateBeatResponseDTO(id, urlAudio, name);
    }
}
