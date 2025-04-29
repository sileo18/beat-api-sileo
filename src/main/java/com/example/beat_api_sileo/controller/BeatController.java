package com.example.beat_api_sileo.controller;


import com.example.beat_api_sileo.domain.Beat.Beat;
import com.example.beat_api_sileo.domain.Beat.CreateBeatRequestDTO;
import com.example.beat_api_sileo.domain.Beat.CreateBeatResponseDTO;
import com.example.beat_api_sileo.mapper.BeatMapper;
import com.example.beat_api_sileo.service.BeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beat")
public class BeatController {

    private final BeatService beatService;

    public BeatController(BeatService beatService) {
        this.beatService = beatService;
    }

    @PostMapping("/upload")
    public ResponseEntity<CreateBeatResponseDTO> uploadBeat(CreateBeatRequestDTO createRequest) {

        Beat beatCreated = beatService.create(createRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BeatMapper.toCreateBeatResponse(beatCreated));

    }

    @DeleteMapping("/delete")
    public String deleteBeat() {

        return "Beat deleted successfully!";
    }


}
