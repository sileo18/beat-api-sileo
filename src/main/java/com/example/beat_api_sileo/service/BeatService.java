package com.example.beat_api_sileo.service;

import com.example.beat_api_sileo.domain.Beat.Beat;
import com.example.beat_api_sileo.domain.Beat.CreateBeatRequestDTO;
import com.example.beat_api_sileo.domain.Beat.Genre;
import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.exceptions.UserNotFound;
import com.example.beat_api_sileo.mapper.BeatMapper;
import com.example.beat_api_sileo.repositories.BeatRepository;
import com.example.beat_api_sileo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeatService {

    public final BeatRepository beatRepository;

    public final S3Service amazonS3;

    public final UserRepository userRepository;

    public BeatService(BeatRepository beatRepository, S3Service amazonS3, UserRepository userRepository) {
        this.beatRepository = beatRepository;
        this.amazonS3 = amazonS3;
        this.userRepository = userRepository;
    }

    public Beat create(CreateBeatRequestDTO data) {

        String imgUrl = "";

        imgUrl = amazonS3.uploadFile(data.image());

        String audioUrl = "";

        audioUrl = amazonS3.uploadFile(data.audio());

        Beat beat = new Beat();

        User user = userRepository.findById(data.userId()).orElseThrow(() -> new UserNotFound("User not found"));

        beat.setName(data.name());
        beat.setBPM(data.bpm());
        beat.setKey(data.key());
        beat.setGenre(data.genre());
        beat.setImageUrl(imgUrl);
        beat.setUser(user);
        beat.setAudioUrl(audioUrl);

        beatRepository.save(beat);

        return beat;
    }

    public List<Beat> getBeatsByGenre(Genre genre) {
        return beatRepository.findByGenre(genre);
    }
}
