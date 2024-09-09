package com.pragma.powerup.stockservice.domains.exceptions;

public class CategoryFieldIsRequiredException extends IllegalArgumentException{
    public CategoryFieldIsRequiredException(String s) {
        super(s);
    }
}
