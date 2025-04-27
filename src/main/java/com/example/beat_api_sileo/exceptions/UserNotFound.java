package com.example.beat_api_sileo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) { super(message);}
}
