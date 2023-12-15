package com.example.isaacwebproject.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
public class ServerNotFoundException extends RuntimeException{
    public ServerNotFoundException(String message) {
        super(message);
    }
}
