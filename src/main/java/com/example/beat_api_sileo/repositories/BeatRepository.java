package com.example.beat_api_sileo.repositories;

import com.example.beat_api_sileo.domain.Beat.Beat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeatRepository extends JpaRepository<Beat, UUID> {

    @Override
    Optional<Beat> findById(UUID uuid);

/*    @Query("SELECT b FROM Beat b JOIN b.genre g WHERE g.id IN :genreIds")
    static List<Beat> findBeatsByGenreIds(@Param("genreIds") List<Long> genreIds);*/

    List<Beat> findByUserId(UUID userId);

    @Query("SELECT b FROM Beat b WHERE b.bpm BETWEEN :minBPM AND :maxBPM")
    List<Beat> findBeatsByBPMRange(@Param("minBPM") int minBPM, @Param("maxBPM") int maxBPM);
}
