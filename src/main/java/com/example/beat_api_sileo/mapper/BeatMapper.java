package com.example.beat_api_sileo.mapper;

import com.example.beat_api_sileo.domain.Beat.Beat;
import com.example.beat_api_sileo.domain.Beat.CreateBeatRequestDTO;
import com.example.beat_api_sileo.domain.Beat.CreateBeatResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BeatMapper {

    public static Beat toBeat(CreateBeatRequestDTO request) {
        return Beat.create(
                request.name(),
                request.bpm()
        );
    }

    public static CreateBeatResponseDTO toCreateBeatResponse(Beat beat) {
        return CreateBeatResponseDTO.create(
                beat.getId(),
                beat.getAudioUrl(),
                beat.getName()
        );
    }
}
