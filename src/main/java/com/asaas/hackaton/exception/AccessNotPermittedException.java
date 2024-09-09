package com.asaas.hackaton.exception;

public class AccessNotPermittedException extends RuntimeException {

    public AccessNotPermittedException(String message) {
        super(message);
    }
}
