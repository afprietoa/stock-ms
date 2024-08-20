package com.pragma.powerup.stockservice.configuration.exceptionhandler;

import com.pragma.powerup.stockservice.configuration.Constants;
import com.pragma.powerup.stockservice.domains.exceptions.EmptyFieldException;
import com.pragma.powerup.stockservice.domains.exceptions.NegativeNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.pragma.powerup.stockservice.configuration.Constants.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NegativeNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handleNegativeNotAllowedException(NegativeNotAllowedException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
