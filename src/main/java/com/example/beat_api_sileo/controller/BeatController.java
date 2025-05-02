package com.example.beat_api_sileo.controller;


import com.example.beat_api_sileo.domain.Beat.Beat;
import com.example.beat_api_sileo.domain.Beat.CreateBeatRequestDTO;
import com.example.beat_api_sileo.domain.Beat.CreateBeatResponseDTO;
import com.example.beat_api_sileo.mapper.BeatMapper;
import com.example.beat_api_sileo.repositories.BeatRepository;
import com.example.beat_api_sileo.service.BeatService;
import com.example.beat_api_sileo.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/beat")
@Tag(name = "Beat Controller", description = "Gerencia operações relacionadas a Beat")
public class BeatController {

    private final BeatService beatService;

    private final GenreService genreService;

    private final BeatRepository beatRepository;

    public BeatController(BeatService beatService, GenreService genreService, BeatRepository beatRepository) {
        this.beatService = beatService;
        this.genreService = genreService;
        this.beatRepository = beatRepository;
    }

    @Operation(summary = "Upload de um novo beat", description = "Cria e salva um novo beat no sistema")
    @PostMapping("/upload")
    public ResponseEntity<CreateBeatResponseDTO> uploadBeat(@Valid @ModelAttribute CreateBeatRequestDTO createRequest) {

        Beat beatCreated = beatService.create(createRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(BeatMapper.toCreateBeatResponse(beatCreated));

    }

    @Operation(summary = "Obter beats por usuário", description = "Retorna uma lista de beats associados a um usuário específico")
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Beat>> getBeatsByUserId(@PathVariable UUID userId) {
        List<Beat> beats = beatService.getBeatsByUserId(userId);
        return ResponseEntity.ok(beats);
    }

    @Operation(summary = "Obter beats por BPM", description = "Retorna uma lista de beats associados a um intervalo de BPM")
    @GetMapping("/get/bpm")
    public ResponseEntity<List<Beat>> getByBpmInterval(@RequestParam  int minBPM,@RequestParam int maxBPM) {
        List<Beat> beats = beatService.getBeatsByBPMInterval(minBPM, maxBPM);
        return ResponseEntity.ok(beats);
    }



    @Operation(summary = "Obter beats recentes", description = "Retorna os beats mais recentes baseado nos request params")
    @GetMapping("/get/most-recent")
    public ResponseEntity<List<Beat>> getMostRecent(@RequestParam int page,@RequestParam int size) {
        List<Beat> mostRecentBeats = beatService.getMostRecent(page, size);

        return ResponseEntity.ok(mostRecentBeats);
    }


    @Operation(summary = "Obter beats por gênero", description = "Retorna beat relacionados ao gênero passado no request")
    @GetMapping("/get/genres")
    public ResponseEntity<List<Beat>> getBeatsByGenres(@RequestParam List<Long> genreIds) {
        List<Beat> beats = beatService.getBeatsByGenres(genreIds);
        return ResponseEntity.ok(beats);
    }

    @Operation(summary = "Deletar beats", description = "Deleta o beat de acordo com seu Id")
    @DeleteMapping("/delete")
    public String deleteBeat() {

        return "Beat deleted successfully!";
    }


}
