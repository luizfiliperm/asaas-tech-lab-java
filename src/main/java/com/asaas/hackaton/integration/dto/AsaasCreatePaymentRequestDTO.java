package com.asaas.hackaton.integration.dto;

import com.asaas.hackaton.dto.PaymentRequestDTO;

import java.math.BigDecimal;

public record AsaasCreatePaymentRequestDTO(
        String description,
        BigDecimal value,
        String dueDate,
        String customer,
        String billingType
) {

    public AsaasCreatePaymentRequestDTO(PaymentRequestDTO paymentRequestDTO, String customer) {
        this(paymentRequestDTO.description(), paymentRequestDTO.value(), paymentRequestDTO.dueDate(), customer, paymentRequestDTO.billingType());
    }
}
