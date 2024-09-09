package com.pragma.powerup.stockservice.domains.exceptions;

public class CategoryFieldIsTooLongException extends IllegalArgumentException{
    public CategoryFieldIsTooLongException(String s) {super(s);};
}
