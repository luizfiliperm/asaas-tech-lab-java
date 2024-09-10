package com.asaas.hackaton.dto;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        String description,
        BigDecimal value,
        String dueDate
) {
}
