package com.example.isaacwebproject.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED, reason = "Socket Already Connected")
public class AlreadyUseSockectException extends RuntimeException {
    public AlreadyUseSockectException(){
        super("Socket Already Connected");
    }
}
