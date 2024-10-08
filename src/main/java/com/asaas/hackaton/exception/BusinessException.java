package com.asaas.hackaton.exception;

public class BusinessException extends RuntimeException {

    String error;

    public BusinessException(String error, String message) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
