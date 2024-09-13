package com.pragma.powerup.stockservice.domains.exceptions;

public class BrandFieldIsRequiredException extends IllegalArgumentException {
    public BrandFieldIsRequiredException(String s) {
        super(s);
    }
}
