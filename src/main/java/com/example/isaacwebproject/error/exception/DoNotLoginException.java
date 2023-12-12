package com.example.isaacwebproject.error.exception;

import jakarta.xml.ws.http.HTTPException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "로그인이 필요한 페이지입니다 ")
public class DoNotLoginException extends RuntimeException {
    public DoNotLoginException() {
        super();
    }
}
