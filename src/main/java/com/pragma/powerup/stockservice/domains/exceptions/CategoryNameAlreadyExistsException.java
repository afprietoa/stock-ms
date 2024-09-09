package com.pragma.powerup.stockservice.domains.exceptions;

public class CategoryNameAlreadyExistsException extends IllegalArgumentException{
    public CategoryNameAlreadyExistsException(String s) {super(s); };
}
