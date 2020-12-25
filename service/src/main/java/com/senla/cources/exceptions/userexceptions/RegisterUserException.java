package com.senla.cources.exceptions.userexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegisterUserException extends RuntimeException {

    public RegisterUserException(Throwable cause) {
        super(cause);
    }
}
