package com.example.beat_api_sileo.service;

import com.example.beat_api_sileo.config.TokenService;

import com.example.beat_api_sileo.domain.Api.RegisterRequestDTO;
import com.example.beat_api_sileo.domain.Api.RegisterResponseDTO;
import com.example.beat_api_sileo.domain.User.UpdateBirthDateRequestDTO;
import com.example.beat_api_sileo.domain.User.UpdateDescriptionRequestDTO;
import com.example.beat_api_sileo.domain.User.User;

import com.example.beat_api_sileo.exceptions.EmailAlreadyExists;
import com.example.beat_api_sileo.exceptions.UserNotFound;
import com.example.beat_api_sileo.mapper.UserMapper;
import com.example.beat_api_sileo.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final S3Service amazonS3;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, S3Service amazonS3, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.amazonS3 = amazonS3;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }
    public User register(RegisterRequestDTO data) {

        String imgUrl = "";

        if (userRepository.existsByEmail(data.email())) {
            throw new EmailAlreadyExists("Email already exists");
        }
        imgUrl = amazonS3.uploadFile(data.picture());

        String encodedPassword = passwordEncoder.encode(data.password());

        User user = UserMapper.toUser(data);

        user.setProfilePictureUrl(imgUrl);
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = tokenService.generateToken(user);

        return user;
    }

    public User updateUserDescription(UpdateDescriptionRequestDTO request) {

        User user = (User) userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFound("User not found"));

        user.setDescription(request.description());

        return userRepository.save(user);
    }

    public void updateUserBirth(UpdateBirthDateRequestDTO request) {

        User user = (User) userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFound("User not found"));

        user.setBirthDate(request.birthdata());

        userRepository.save(user);
    }

    public User getUserByEmail(String email) {

        User user =(User) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound("User not found"));

        return user;
    }


}
