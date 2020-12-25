package com.senla.cources.exceptions.groupexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class RemoveUserFromGroupException extends RuntimeException {

    public RemoveUserFromGroupException(Throwable cause) {
        super(cause);
    }
}
