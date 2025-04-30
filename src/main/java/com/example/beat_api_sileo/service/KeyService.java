package com.example.beat_api_sileo.service;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Key.Key;
import com.example.beat_api_sileo.repositories.GenreRepository;
import com.example.beat_api_sileo.repositories.KeyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeyService {

    private final KeyRepository keyRepository;

    public KeyService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    public Key save(Key key) {
        return keyRepository.save(key);
    }

    public List<Key> findAll() {
        return keyRepository.findAll();
    }

    public void deleteById(Long id) {
        keyRepository.deleteById(id);
    }

    public Optional<Key> findById(Long id) {
        return keyRepository.findById(id);
    }
}
