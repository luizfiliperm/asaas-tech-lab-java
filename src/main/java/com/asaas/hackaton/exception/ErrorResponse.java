package com.asaas.hackaton.exception;

public record ErrorResponse(
        String error,
        String message
) {
}
