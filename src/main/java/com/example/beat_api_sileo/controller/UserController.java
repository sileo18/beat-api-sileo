package com.example.beat_api_sileo.controller;

import com.example.beat_api_sileo.config.TokenService;
import com.example.beat_api_sileo.domain.Api.LoginRequestDTO;
import com.example.beat_api_sileo.domain.Api.LoginResponseDTO;
import com.example.beat_api_sileo.domain.Api.RegisterRequestDTO;
import com.example.beat_api_sileo.domain.Api.RegisterResponseDTO;
import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.mapper.UserMapper;
import com.example.beat_api_sileo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "Gerencia operações relacionadas a User")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Autowired
    private UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Operation(summary = "Registra um novo usuário", description = "Cria e salve um novo usuário em sistema")
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegisterResponseDTO> registerUser(@Valid @ModelAttribute
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("picture") MultipartFile picture) {


        RegisterRequestDTO request = new RegisterRequestDTO(name, email, surname, password, picture);


        User savedUser = userService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toRegisterResponse(savedUser));
    }

    @Operation(summary = "Autentica um usuário", description = "Autentica um usuário a partir de email e senha")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.email() , loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(authToken);

        User user =(User) authentication.getPrincipal();

        String token = tokenService.generateToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token, user.getSurname()));
    }

    @Operation(summary = "Busca usuário por email", description = "Retorna um usuário através de seu email")
    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


}
