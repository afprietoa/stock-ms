package com.pragma.powerup.stockservice.domains.exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String message) {
        super(message);
    }
}
