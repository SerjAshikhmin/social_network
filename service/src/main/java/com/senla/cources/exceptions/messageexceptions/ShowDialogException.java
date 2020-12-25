package com.senla.cources.exceptions.messageexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShowDialogException extends RuntimeException {

    public ShowDialogException(Throwable cause) {
        super(cause);
    }
}
