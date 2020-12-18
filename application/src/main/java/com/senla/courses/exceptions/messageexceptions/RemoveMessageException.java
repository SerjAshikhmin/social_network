package com.senla.courses.exceptions.messageexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class RemoveMessageException extends RuntimeException {

    public RemoveMessageException(Throwable cause) {
        super(cause);
    }
}
