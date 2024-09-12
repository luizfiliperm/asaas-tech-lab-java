package com.asaas.hackaton.controller;

import com.asaas.hackaton.exception.AccessNotPermittedException;
import com.asaas.hackaton.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> httpClientError(HttpClientErrorException exception) {
        return ResponseEntity.ok(exception.getResponseBodyAsString());
    }
}