package com.future.productservice.ControllerAdvices;

import com.future.productservice.Exceptions.CustomExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptions.class)
    private ResponseEntity<String> ProductNotFoundException(CustomExceptions e){
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> ProductException(Exception e){
        ResponseEntity<String> response = new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_GATEWAY
        );
        return response;
    }
}
