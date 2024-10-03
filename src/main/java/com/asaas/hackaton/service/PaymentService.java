package com.asaas.hackaton.service;

import com.asaas.hackaton.dto.ListPaymentResponseDTO;
import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.dto.PaymentResponseDTO;
import com.asaas.hackaton.integration.AsaasClient;
import com.asaas.hackaton.integration.dto.AsaasCreatePaymentRequestDTO;
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
        String customer = asaasClient.createCustomer(paymentRequestDTO.customer());
        AsaasCreatePaymentRequestDTO asaasCreatePaymentRequestDTO = new AsaasCreatePaymentRequestDTO(paymentRequestDTO, customer);
        return asaasClient.createPayment(asaasCreatePaymentRequestDTO);
    }

    public ListPaymentResponseDTO list() {
        return asaasClient.listPayment();
    }

    public PaymentResponseDTO findById(String id) {
        return asaasClient.findPaymentById(id);
    }
}
