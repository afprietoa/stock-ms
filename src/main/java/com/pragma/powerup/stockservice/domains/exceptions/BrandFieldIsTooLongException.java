package com.pragma.powerup.stockservice.domains.exceptions;

public class BrandFieldIsTooLongException extends IllegalArgumentException{
    public BrandFieldIsTooLongException(String s) {
        super(s);
    }
}
