package com.senla.courses.exceptions.messageexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PostMessageException extends RuntimeException {

    public PostMessageException(Throwable cause) {
        super(cause);
    }
}
