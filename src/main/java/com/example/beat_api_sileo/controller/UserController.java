package com.example.beat_api_sileo.controller;

import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.domain.User.UserRegisterDTO;
import com.example.beat_api_sileo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegisterDTO user) {

        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }
}
