package com.senla.courses.exceptions.groupexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class RemoveGroupException extends RuntimeException {

    public RemoveGroupException(Throwable cause) {
        super(cause);
    }
}
