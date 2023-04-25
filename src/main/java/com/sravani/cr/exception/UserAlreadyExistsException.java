package com.sravani.cr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String exception) {
        super(exception);
    }
}
