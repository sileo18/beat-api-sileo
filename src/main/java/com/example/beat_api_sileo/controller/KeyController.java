package com.example.beat_api_sileo.controller;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Genre.GenreRequestDTO;
import com.example.beat_api_sileo.domain.Genre.GenreResponseDTO;
import com.example.beat_api_sileo.domain.Key.Key;
import com.example.beat_api_sileo.domain.Key.KeyRequestDTO;
import com.example.beat_api_sileo.domain.Key.KeyResponseDTO;
import com.example.beat_api_sileo.mapper.GenreMapper;
import com.example.beat_api_sileo.mapper.KeyMapper;
import com.example.beat_api_sileo.service.GenreService;
import com.example.beat_api_sileo.service.KeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key")
@Tag(name = "Key Controller", description = "Gerencia operações relacionadas a Key")
public class KeyController {
    private final KeyService keyService;

    public KeyController(KeyService keyService) {
        this.keyService = keyService;
    }

    @Operation(summary = "Cadastra novas key's", description = "Cria e salve novas key's em sistema")
    @PostMapping("/create")
    public ResponseEntity<KeyResponseDTO> createKey(@Valid @RequestBody KeyRequestDTO keyRequest) {
        Key savedKey = keyService.save(KeyMapper.toKey(keyRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(KeyMapper.toKeyReponse(savedKey));
    }

    @Operation(summary = "Busca todas key's", description = "Retorna todas key's salvas em sistema")
    @GetMapping("/all")
    public ResponseEntity<List<KeyResponseDTO>> getAllKey() {
        List<KeyResponseDTO> keys = keyService.findAll().stream()
                .map(KeyMapper::toKeyReponse)
                .toList();

        return ResponseEntity.ok(keys);
    }

    @Operation(summary = "Busca uma key", description = "Retorna uma key salvo em sistema através do Id")
    @GetMapping("/{id}")
    public ResponseEntity<KeyResponseDTO> getKeyById(@PathVariable Long id) {
        Key key = keyService.findById(id).orElseThrow(() -> new RuntimeException("Genre not found"));
        return ResponseEntity.ok(KeyMapper.toKeyReponse(key));
    }

    @Operation(summary = "Deleta uma key", description = "Deleta uma key através do seu Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKey(@PathVariable Long id) {
        try {
            keyService.deleteById(id);
        }
        catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.noContent().build();
    }

}
