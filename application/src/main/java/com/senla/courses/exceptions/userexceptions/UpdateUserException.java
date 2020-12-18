package com.senla.courses.exceptions.userexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class UpdateUserException extends RuntimeException {

    public UpdateUserException(Throwable cause) {
        super(cause);
    }
}
