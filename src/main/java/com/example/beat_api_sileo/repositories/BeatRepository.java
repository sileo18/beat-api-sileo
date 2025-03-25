package com.example.beat_api_sileo.repositories;

import com.example.beat_api_sileo.domain.Beat.Beat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeatRepository extends JpaRepository<Beat, UUID> {
}
