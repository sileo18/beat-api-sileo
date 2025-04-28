package com.example.beat_api_sileo.mapper;

import com.example.beat_api_sileo.domain.Api.RegisterRequestDTO;
import com.example.beat_api_sileo.domain.Api.RegisterResponseDTO;
import com.example.beat_api_sileo.domain.User.User;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@Builder
@UtilityClass
public class UserMapper {

    public static User toUser(RegisterRequestDTO request) {
        return User.create(
                request.name(),
                request.surname(),
                request.email(),
                request.password()
        );
    }

    public static RegisterResponseDTO toRegisterResponse(User user) {
        return RegisterResponseDTO.create(
                user.getId(),
                user.getName(),
                user.getEmail()

        );
    }
}
