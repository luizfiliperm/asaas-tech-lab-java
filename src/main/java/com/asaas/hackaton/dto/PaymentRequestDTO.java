package com.asaas.hackaton.dto;

import java.math.BigDecimal;
import java.util.Objects;

public record PaymentRequestDTO(
        String description,
        BigDecimal value,
        String dueDate,
        CustomerAccountDTO customer,
        String billingType
) {
    public PaymentRequestDTO {
        Objects.requireNonNull(value, "É necessário informar um valor");
        Objects.requireNonNull(dueDate, "É necessário informar uma data de vencimento");
        Objects.requireNonNull(customer, "É necessário informar um cliente");
        Objects.requireNonNull(billingType, "É necessário informar a forma de pagamento");
    }
}
