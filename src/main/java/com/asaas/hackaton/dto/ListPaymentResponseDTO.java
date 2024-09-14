package com.asaas.hackaton.dto;

import java.util.List;

public record ListPaymentResponseDTO(
        int totalCount,
        int limit,
        int offset,
        List<PaymentResponseDTO> data
) {
}
