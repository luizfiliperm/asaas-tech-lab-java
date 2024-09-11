package com.asaas.hackaton.dto;

import java.math.BigDecimal;
import java.util.Objects;

public record PaymentRequestDTO(
        String description,
        BigDecimal value,
        String dueDate
) {
    public PaymentRequestDTO {
        Objects.requireNonNull(value, "É necessário informar um valor");
        Objects.requireNonNull(dueDate, "É necessário informar uma data de vencimento");
    }
}
