package com.asaas.hackaton.dto;

import java.math.BigDecimal;

public record PaymentResponseDTO(
        String id,
        BigDecimal value,
        String dueDate,
        String description,
        String billingType,
        String status,
        String customer
) {
}
