package com.asaas.hackaton.dto.transfer;

import java.math.BigDecimal;

public record TransferRequestDTO(
        BigDecimal value,
        TransferOperationType operationType,
        String pixAddressKey,
        TransferPixAddressKeyType pixAddressKeyType
) {
}
