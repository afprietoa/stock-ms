package com.pragma.powerup.stockservice.domains.exceptions;

public class ProductCategoryRepeatedException extends IllegalArgumentException{
    public ProductCategoryRepeatedException(String s) {
        super(s);
    }
}
