package com.senla.cources.exceptions.userexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class RemoveFromFriendsException extends RuntimeException {

    public RemoveFromFriendsException(Throwable cause) {
        super(cause);
    }
}
