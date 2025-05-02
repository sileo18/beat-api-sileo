package com.example.beat_api_sileo.controller;


import com.example.beat_api_sileo.domain.Beat.Beat;
import com.example.beat_api_sileo.domain.Beat.CreateBeatRequestDTO;
import com.example.beat_api_sileo.domain.Beat.CreateBeatResponseDTO;
import com.example.beat_api_sileo.mapper.BeatMapper;
import com.example.beat_api_sileo.repositories.BeatRepository;
import com.example.beat_api_sileo.service.BeatService;
import com.example.beat_api_sileo.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/beat")
public class BeatController {

    private final BeatService beatService;

    private final GenreService genreService;

    private final BeatRepository beatRepository;

    public BeatController(BeatService beatService, GenreService genreService, BeatRepository beatRepository) {
        this.beatService = beatService;
        this.genreService = genreService;
        this.beatRepository = beatRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<CreateBeatResponseDTO> uploadBeat(@Valid @ModelAttribute CreateBeatRequestDTO createRequest) {

        Beat beatCreated = beatService.create(createRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(BeatMapper.toCreateBeatResponse(beatCreated));

    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<Beat>> getBeatsByUserId(@PathVariable UUID userId) {
        List<Beat> beats = beatService.getBeatsByUserId(userId);
        return ResponseEntity.ok(beats);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Beat>> getByBpmInterval(@RequestParam  int minBPM,@RequestParam int maxBPM) {
        List<Beat> beats = beatService.getBeatsByBPMInterval(minBPM, maxBPM);
        return ResponseEntity.ok(beats);
    }


    @GetMapping("/get/most-recent")
    public ResponseEntity<List<Beat>> getMostRecent() {
        List<Beat> mostRecentBeats = beatService.getMostRecent();

        return ResponseEntity.ok(mostRecentBeats);
    }


    @GetMapping("/by-genres")
    public ResponseEntity<List<Beat>> getBeatsByGenres(@RequestParam List<Long> genreIds) {
        List<Beat> beats = beatService.getBeatsByGenres(genreIds);
        return ResponseEntity.ok(beats);
    }

    @DeleteMapping("/delete")
    public String deleteBeat() {

        return "Beat deleted successfully!";
    }


}
