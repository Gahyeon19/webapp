package com.example.validation_ex.exception;

public class ItemValidationException extends RuntimeException{
    public ItemValidationException() {
        super();
    }

    public ItemValidationException(String message) {
        super(message);
    }

    public ItemValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemValidationException(Throwable cause) {
        super(cause);
    }

    protected ItemValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
