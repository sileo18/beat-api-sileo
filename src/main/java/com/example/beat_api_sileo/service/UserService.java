package com.example.beat_api_sileo.service;

import com.example.beat_api_sileo.domain.User.UserRegisterDTO;
import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User createUser(UserRegisterDTO data) {



        if(data.getProfilePictureUrl() != null) {
            // Do something
        }

        User user = new User();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setSurname(data.getSurname());
        user.setDescription(data.getDescription());
        user.setBirthDate(data.getBirthDate());
        user.setProfilePictureUrl(data.getProfilePictureUrl());

        userRepository.save(user);

        return user;

    }
}
