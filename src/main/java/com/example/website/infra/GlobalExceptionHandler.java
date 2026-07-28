package com.example.website.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(EntityNotFoundException enfe){
        ErrorResponse e = new ErrorResponse(HttpStatus.NOT_FOUND.value(), enfe.getMessage());
        return ResponseEntity.status(e.getStatus()).body(e);
    }
}
