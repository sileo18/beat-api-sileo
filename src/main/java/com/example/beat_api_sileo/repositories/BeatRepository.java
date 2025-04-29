package com.example.beat_api_sileo.repositories;

import com.example.beat_api_sileo.domain.Beat.Beat;
import com.example.beat_api_sileo.domain.Beat.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeatRepository extends JpaRepository<Beat, UUID> {

    @Override
    Optional<Beat> findById(UUID uuid);

    List<Beat> findByGenre(Genre genre);

}
