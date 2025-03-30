package com.example.beat_api_sileo.controller;

import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.domain.User.UserRegisterDTO;
import com.example.beat_api_sileo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @ModelAttribute UserRegisterDTO user) {

        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }
}
