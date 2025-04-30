package com.example.beat_api_sileo.service;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

}
