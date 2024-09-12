package com.asaas.hackaton.service;

import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.dto.PaymentResponseDTO;
import com.asaas.hackaton.integration.AsaasClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PaymentService {

    private final AsaasClient asaasClient;

    public PaymentService(AsaasClient asaasClient) {
        this.asaasClient = asaasClient;
    }

    public PaymentResponseDTO create(PaymentRequestDTO paymentRequestDTO) {
        asaasClient.createCustomer(paymentRequestDTO.customer());
        return asaasClient.createPayment(paymentRequestDTO);
    }
}
