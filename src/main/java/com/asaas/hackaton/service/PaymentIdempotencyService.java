package com.asaas.hackaton.service;

import com.asaas.hackaton.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PaymentIdempotencyService {

    private final Map<String, PaymentResponseDTO> processedRequests = new ConcurrentHashMap<>();

    public PaymentResponseDTO findPaymentResponseByKey(String key) {
        return processedRequests.get(key);
    }

    public void markAsProcessed(String key, PaymentResponseDTO responseDTO) {
        processedRequests.put(key, responseDTO);
    }
}
