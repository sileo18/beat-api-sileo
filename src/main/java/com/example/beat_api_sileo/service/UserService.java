package com.example.beat_api_sileo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.beat_api_sileo.config.TokenService;
import com.example.beat_api_sileo.domain.Api.LoginRequestDTO;
import com.example.beat_api_sileo.domain.Api.LoginResponseDTO;
import com.example.beat_api_sileo.domain.Api.RegisterResponseDTO;
import com.example.beat_api_sileo.domain.Roles.RoleEnum;
import com.example.beat_api_sileo.domain.Roles.Roles;
import com.example.beat_api_sileo.domain.User.UserRegisterDTO;
import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.domain.UserRole.UserRole;
import com.example.beat_api_sileo.exceptions.EmailAlreadyExists;
import com.example.beat_api_sileo.exceptions.InvalidCredentials;
import com.example.beat_api_sileo.exceptions.UserNotFound;
import com.example.beat_api_sileo.repositories.RolesRepository;
import com.example.beat_api_sileo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private S3Service amazonS3;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public RegisterResponseDTO createUser(UserRegisterDTO data) {

        String imgUrl = "";

        if (userRepository.existsByEmail(data.getEmail())) {
            throw new EmailAlreadyExists("Email already exists");
        }

        if(data.getProfilePictureUrl() != null) {
            imgUrl = amazonS3.uploadFile(data.getProfilePictureUrl());
        }

        String encodedPassword = passwordEncoder.encode(data.getPassword());


        User user = new User();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(encodedPassword);
        user.setSurname(data.getSurname());
        user.setDescription(data.getDescription());
        user.setBirthDate(new Date(data.getBirthDate()));
        user.setProfilePictureUrl(imgUrl);

        Roles role = rolesRepository.findByName(RoleEnum.ADMIN)
                .orElseThrow(() -> new RuntimeException("Role não encontrada: " + RoleEnum.ADMIN));

        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        String token = tokenService.generateToken(user);

        return new RegisterResponseDTO(user, token);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {

        String email = loginRequest.getEmail().trim();
        String password = loginRequest.getPassword();

        System.out.println("Attempting to login with email: " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentials("Invalid credentials");
        }

        String token = tokenService.generateToken(user);

        return new LoginResponseDTO(token, user.getName(), user.getId());
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
