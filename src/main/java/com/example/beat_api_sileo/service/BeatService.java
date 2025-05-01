package com.example.beat_api_sileo.service;

import com.example.beat_api_sileo.domain.Beat.Beat;
import com.example.beat_api_sileo.domain.Beat.CreateBeatRequestDTO;
import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Key.Key;
import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.exceptions.UserNotFound;
import com.example.beat_api_sileo.repositories.BeatRepository;
import com.example.beat_api_sileo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeatService {

    public final BeatRepository beatRepository;

    public final S3Service amazonS3;

    public final UserRepository userRepository;

    public final KeyService keyService;

    private final GenreService genreService;


    public BeatService(BeatRepository beatRepository, S3Service amazonS3, UserRepository userRepository, KeyService keyService, GenreService genreService) {
        this.beatRepository = beatRepository;
        this.amazonS3 = amazonS3;
        this.userRepository = userRepository;
        this.keyService = keyService;
        this.genreService = genreService;
    }

    public Beat create(CreateBeatRequestDTO data) {

        String imgUrl = "";

        imgUrl = amazonS3.uploadFile(data.image());

        String audioUrl = "";

        audioUrl = amazonS3.uploadFile(data.audio());

        Beat beat = new Beat();

        Key key = keyService.findById(data.keyId()).orElseThrow(() -> new RuntimeException("Key not found"));

        List<Genre> genres = data.genresId().stream()
                .map(genreId -> genreService.findById(genreId)
                        .orElseThrow(() -> new RuntimeException("Genre not found")))
                .toList();

        User user = userRepository.findById(data.userId()).orElseThrow(() -> new UserNotFound("User not found"));

        beat.setName(data.name());
        beat.setBPM(data.bpm());
        beat.setKey(key);
        beat.setGenre(genres);
        beat.setImageUrl(imgUrl);
        beat.setUser(user);
        beat.setAudioUrl(audioUrl);

        beatRepository.save(beat);

        return beat;
    }

    public List<Beat> getBeatsByUserId(UUID userId) {
        return beatRepository.findByUserId(userId);
    }

    /*public List<Beat> getBeatsByGenres(List<Long> genreIds) {
        return BeatRepository.findBeatsByGenreIds(genreIds);
    }*/
}
