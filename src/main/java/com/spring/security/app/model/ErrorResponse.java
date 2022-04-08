package com.spring.security.app.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    String message;
    List<String> errors;

    public ErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        if (errors == null) {
            return new ArrayList<>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
       this.errors = errors;
    }
}
