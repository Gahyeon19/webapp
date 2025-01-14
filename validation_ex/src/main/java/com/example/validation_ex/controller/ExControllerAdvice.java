package com.example.validation_ex.controller;

import com.example.validation_ex.dto.ErrorResult;
import com.example.validation_ex.dto.MemberErrorResult;
import com.example.validation_ex.exception.ItemValidationException;
import com.example.validation_ex.exception.MemberValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public MemberErrorResult memberExceptionHandler(MemberValidationException ex) {
        return new MemberErrorResult(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult ItemExceptionHandler(ItemValidationException ex) {
        return new ErrorResult(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
