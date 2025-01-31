package com.mak.starter.exceptions;

import java.util.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // @ExceptionHandler(AccountNotFoundException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // @ResponseBody
    // public ErrorResponse handleAccountNotFoundException(AccountNotFoundException ex) {
    //     return new ErrorResponse(ex.getMessage());
    // }

    private String getErrorsMap(List<ObjectError> list) {
        Map<String, String> errors = new HashMap<>();
        list.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)    
    @ResponseBody    
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ErrorResponse(getErrorsMap(ex.getBindingResult().getAllErrors()));
    }

    // @ExceptionHandler(DataIntegrityViolationException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ResponseBody
    // public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        
    //     return new ErrorResponse(ErrorMessages.CONSTRAINT_VIOLATION_EXCEPTION.getMessage());
        
    // }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(HttpMessageNotReadableException ex) {
        return new ErrorResponse(ErrorMessages.BAD_REQUEST.getMessage());
        
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ErrorResponse(ErrorMessages.BAD_REQUEST.getMessage());
        
    }
}
