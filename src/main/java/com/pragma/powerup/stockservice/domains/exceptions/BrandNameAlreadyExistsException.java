package com.pragma.powerup.stockservice.domains.exceptions;

public class BrandNameAlreadyExistsException extends IllegalArgumentException{
    public BrandNameAlreadyExistsException(String s) {
        super(s);
    }
}
