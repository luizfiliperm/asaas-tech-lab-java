package com.asaas.hackaton.controller;

import com.asaas.hackaton.assets.AccessNotPermittedException;
import com.asaas.hackaton.assets.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound() {
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessNotPermittedException.class)
    public void handleForbidden() {
    }
}