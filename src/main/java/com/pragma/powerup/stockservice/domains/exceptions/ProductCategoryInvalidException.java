package com.pragma.powerup.stockservice.domains.exceptions;

public class ProductCategoryInvalidException extends IllegalArgumentException{
    public ProductCategoryInvalidException(String s) {
        super(s);
    }
}
