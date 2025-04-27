package com.example.beat_api_sileo.controller;

import com.example.beat_api_sileo.domain.Api.LoginRequestDTO;
import com.example.beat_api_sileo.domain.Api.LoginResponseDTO;
import com.example.beat_api_sileo.domain.Api.RegisterResponseDTO;
import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.domain.User.UserRegisterDTO;
import com.example.beat_api_sileo.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
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
    public ResponseEntity<RegisterResponseDTO> registerUser(@Valid @ModelAttribute UserRegisterDTO user) {

        RegisterResponseDTO newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO token = userService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


}
