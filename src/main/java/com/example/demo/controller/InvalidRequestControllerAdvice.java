package com.example.demo.controller;

import com.example.demo.common.InvalidRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidRequestControllerAdvice {

    @ExceptionHandler(InvalidRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidRequest() {
    }
}
