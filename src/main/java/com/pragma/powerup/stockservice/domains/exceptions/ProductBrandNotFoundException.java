package com.pragma.powerup.stockservice.domains.exceptions;

public class ProductBrandNotFoundException extends IllegalArgumentException{
    public ProductBrandNotFoundException(String s) {
        super(s);
    }
}
