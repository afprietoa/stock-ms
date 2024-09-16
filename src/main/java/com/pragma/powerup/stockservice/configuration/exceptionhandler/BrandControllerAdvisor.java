package com.pragma.powerup.stockservice.configuration.exceptionhandler;

import com.pragma.powerup.stockservice.domains.exceptions.BrandFieldIsRequiredException;
import com.pragma.powerup.stockservice.domains.exceptions.BrandFieldIsTooLongException;
import com.pragma.powerup.stockservice.domains.exceptions.BrandNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BrandControllerAdvisor {

    @ExceptionHandler(BrandFieldIsRequiredException.class)
    public ResponseEntity<Object> handleBrandFieldIsRequiredException(BrandFieldIsRequiredException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BrandFieldIsTooLongException.class)
    public ResponseEntity<Object> handleBrandFieldIsTooLongException(BrandFieldIsTooLongException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BrandNameAlreadyExistsException.class)
    public ResponseEntity<Object> handleBrandNameAlreadyExistsException(BrandNameAlreadyExistsException ex) {
        return buildResponseEntity(HttpStatus.CONFLICT, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}