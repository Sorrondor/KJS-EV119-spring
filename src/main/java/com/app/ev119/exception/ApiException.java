package com.app.ev119.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
