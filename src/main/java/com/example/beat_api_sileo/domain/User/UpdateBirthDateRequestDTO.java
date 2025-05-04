package com.example.beat_api_sileo.domain.User;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public record UpdateBirthDateRequestDTO(String email,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdata) {
}
