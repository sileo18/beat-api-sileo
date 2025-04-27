package com.example.beat_api_sileo.domain.Api;

import com.example.beat_api_sileo.domain.User.User;

public class RegisterResponseDTO {

    public User user;
    public String token;

    public RegisterResponseDTO(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
