package com.example.beat_api_sileo.domain.Api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record RegisterRequestDTO(

        @NotBlank(message = "Name cannot be blank")
        String name,
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Surname cannot be blank")
        String surname,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,
        @NotBlank(message = "Picture cannot be blank")
        MultipartFile picture) {

    public static RegisterRequestDTO create(String name, String email, String surname, String password, MultipartFile picture) {
        return new RegisterRequestDTO(name, email, surname, password, picture);
    }
}
