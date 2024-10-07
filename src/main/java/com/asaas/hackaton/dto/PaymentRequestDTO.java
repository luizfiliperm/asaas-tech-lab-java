package com.asaas.hackaton.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Objects;

public record PaymentRequestDTO(

        @Schema(example = "loren")
        String description,
        @Schema(example = "50.0")
        BigDecimal value,
        @Schema(example = "2024-10-30")
        String dueDate,
        CustomerAccountRequestDTO customer,
        @Schema(example = "BOLETO")
        String billingType
) {
    public PaymentRequestDTO {
        Objects.requireNonNull(value, "É necessário informar um valor");
        Objects.requireNonNull(dueDate, "É necessário informar uma data de vencimento");
        Objects.requireNonNull(customer, "É necessário informar um cliente");
        Objects.requireNonNull(billingType, "É necessário informar a forma de pagamento");
    }
}
