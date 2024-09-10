package com.asaas.hackaton.dto;

import com.asaas.hackaton.util.CustomDateUtils;

import java.math.BigDecimal;
import java.util.Date;

public record PaymentResponseDTO(
        String description,
        String dueDate,
        BigDecimal value
) {

    public static PaymentResponseDTO from(String description, Date dueDate, BigDecimal value) {
        String formattedDueDate = CustomDateUtils.formatDate(dueDate);
        return new PaymentResponseDTO(description, formattedDueDate, value);
    }
}
