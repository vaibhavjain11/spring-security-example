package com.spring.security.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException  extends Exception{

    public UserAlreadyExistsException(String e) {
        super(e);
    }

    UserAlreadyExistsException(String message, Throwable e) {
        super(message, e);
    }
}
