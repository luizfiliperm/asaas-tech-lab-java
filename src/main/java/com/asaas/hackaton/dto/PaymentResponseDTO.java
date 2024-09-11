package com.asaas.hackaton.dto;

import com.asaas.hackaton.util.CustomDateUtils;

import java.math.BigDecimal;
import java.util.Date;

public record PaymentResponseDTO(
        BigDecimal value,
        String dueDate,
        String description
) {

    public static PaymentResponseDTO from(BigDecimal value, Date dueDate, String description) {
        String formattedDueDate = CustomDateUtils.formatDate(dueDate);
        return new PaymentResponseDTO(value, formattedDueDate, description);
    }
}
