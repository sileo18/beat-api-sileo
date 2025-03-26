package com.example.beat_api_sileo.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Birth date is mandatory")
    private Date birthDate;
    @NotBlank(message = "Profile picture is mandatory")
    private String profilePictureUrl;


}
