package com.example.beat_api_sileo.controller;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Genre.GenreRequestDTO;
import com.example.beat_api_sileo.domain.Genre.GenreResponseDTO;
import com.example.beat_api_sileo.mapper.GenreMapper;
import com.example.beat_api_sileo.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenreResponseDTO> createGenre(@Valid @RequestBody GenreRequestDTO categoryRequest) {
        Genre savedGenre = genreService.save(GenreMapper.toGenre(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(GenreMapper.toGenreReponse(savedGenre));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres() {
        List<GenreResponseDTO> genres = genreService.findAll().stream()
                .map(GenreMapper::toGenreReponse)
                .toList();

        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getGenreById(@PathVariable Long id) {
        Genre genre = genreService.findById(id).orElseThrow(() -> new RuntimeException("Genre not found"));
        return ResponseEntity.ok(GenreMapper.toGenreReponse(genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        try {
            genreService.deleteById(id);
        }
        catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.noContent().build();
    }
}
