package com.example.website.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(EntityNotFoundException enfe){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse e = new ErrorResponse(status, enfe.getMessage());
        return ResponseEntity.status(status).body(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> errors = new HashMap<>();

        for(FieldError error : manve.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse e = new ErrorResponse(status, ErrorMessages.VALIDATION_FAILED, errors);
        return ResponseEntity.status(status).body(e);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException eaee){
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse e = new ErrorResponse(status, eaee.getMessage());
        return ResponseEntity.status(status).body(e);
    }


}
