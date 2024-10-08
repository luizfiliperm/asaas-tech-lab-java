package com.asaas.hackaton.jobs;

import com.asaas.hackaton.dto.transfer.TransferOperationType;
import com.asaas.hackaton.dto.transfer.TransferPixAddressKeyType;
import com.asaas.hackaton.dto.transfer.TransferRequestDTO;
import com.asaas.hackaton.dto.transfer.TransferResponseDTO;
import com.asaas.hackaton.service.TransferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateDailyTransferJob {

    private final TransferService transferService;

    private static final BigDecimal TRANSFER_AMOUNT = new BigDecimal("100.00");

    @Value("${api.pix.address.key}")
    private String pixKey;

    public CreateDailyTransferJob(TransferService transferService) {
        this.transferService = transferService;
    }

    @Scheduled(cron = "0 0 8,12 * * ?")
    public void executeTransferJob() {
        System.out.println("Executando o Job de Transferências diárias...");

        BigDecimal balance = transferService.retrieveFineBalance();

        if (balance.compareTo(TRANSFER_AMOUNT) > 0) {
            TransferRequestDTO transferRequestDTO = new TransferRequestDTO(
                    TRANSFER_AMOUNT,
                    TransferOperationType.PIX,
                    pixKey,
                    TransferPixAddressKeyType.EVP
            );

            TransferResponseDTO response = transferService.create(transferRequestDTO);
            System.out.println("Transferência criada com sucesso: " + response);
        } else {
            System.out.println("Saldo insuficiente para realizar transferências: " + balance);
        }
    }
}