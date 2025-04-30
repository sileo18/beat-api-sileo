package com.example.beat_api_sileo.repositories;

import com.example.beat_api_sileo.domain.Genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {



}
