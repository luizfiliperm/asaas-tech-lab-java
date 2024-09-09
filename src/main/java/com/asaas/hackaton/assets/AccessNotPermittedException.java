package com.asaas.hackaton.assets;

public class AccessNotPermittedException extends RuntimeException {

    public AccessNotPermittedException(String message) {
        super(message);
    }
}
