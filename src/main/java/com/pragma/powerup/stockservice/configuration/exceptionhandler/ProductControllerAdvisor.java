package com.pragma.powerup.stockservice.configuration.exceptionhandler;

import com.pragma.powerup.stockservice.domains.exceptions.ProductBrandNotFoundException;
import com.pragma.powerup.stockservice.domains.exceptions.ProductCategoryInvalidException;
import com.pragma.powerup.stockservice.domains.exceptions.ProductCategoryRepeatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProductControllerAdvisor {

    @ExceptionHandler(ProductCategoryInvalidException.class)
    public ResponseEntity<Object> handleProductCategoryInvalidException(ProductCategoryInvalidException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ProductCategoryRepeatedException.class)
    public ResponseEntity<Object> handleProductCategoryRepeatedException(ProductCategoryRepeatedException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ProductBrandNotFoundException.class)
    public ResponseEntity<Object> handleProductBrandNotFoundException(ProductBrandNotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}