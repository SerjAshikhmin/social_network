package com.senla.courses.exceptions.groupexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddUserToGroupException extends RuntimeException {

    public AddUserToGroupException(Throwable cause) {
        super(cause);
    }
}
