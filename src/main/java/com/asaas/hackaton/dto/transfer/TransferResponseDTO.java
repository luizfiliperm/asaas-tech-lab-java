package com.asaas.hackaton.dto.transfer;

import java.math.BigDecimal;

public record TransferResponseDTO(
        String id,
        TransferStatus status,
        BigDecimal value
) {
}
