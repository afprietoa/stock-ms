package com.pragma.powerup.stockservice.configuration.exceptionhandler;

import com.pragma.powerup.stockservice.domains.exceptions.CategoryFieldIsRequiredException;
import com.pragma.powerup.stockservice.domains.exceptions.CategoryFieldIsTooLongException;
import com.pragma.powerup.stockservice.domains.exceptions.CategoryNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CategoryControllerAdvisor {

    @ExceptionHandler(CategoryFieldIsRequiredException.class)
    public ResponseEntity<Object> handleCategoryFieldIsRequiredException(CategoryFieldIsRequiredException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CategoryFieldIsTooLongException.class)
    public ResponseEntity<Object> handleCategoryFieldIsTooLongException(CategoryFieldIsTooLongException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CategoryNameAlreadyExistsException.class)
    public ResponseEntity<Object> handleCategoryNameAlreadyExistsException(CategoryNameAlreadyExistsException ex) {
        return buildResponseEntity(HttpStatus.CONFLICT, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
