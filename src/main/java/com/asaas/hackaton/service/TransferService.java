package com.asaas.hackaton.service;

import com.asaas.hackaton.dto.transfer.TransferRequestDTO;
import com.asaas.hackaton.dto.transfer.TransferResponseDTO;
import com.asaas.hackaton.integration.AsaasClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final AsaasClient asaasClient;

    public TransferService(AsaasClient asaasClient) {
        this.asaasClient = asaasClient;
    }

    public TransferResponseDTO create(TransferRequestDTO transferRequestDTO) {
        return asaasClient.createTransfer(transferRequestDTO);
    }

    public BigDecimal retrieveFineBalance() {
        return asaasClient.retrieveFineBalance();
    }

}
