package com.senla.courses.exceptions.groupexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddGroupException extends RuntimeException {

    public AddGroupException(Throwable cause) {
        super(cause);
    }
}
