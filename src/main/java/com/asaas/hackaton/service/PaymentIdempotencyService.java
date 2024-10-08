package com.asaas.hackaton.service;

import com.asaas.hackaton.dto.PaymentRequestDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class PaymentIdempotencyService {

    private final ConcurrentHashMap<String, Long> processedRequests = new ConcurrentHashMap<>();

    public boolean isRequestProcessed(String key, Long durationInSeconds) {
        Long timestamp = processedRequests.get(key);
        if (timestamp != null) {
            long elapsedTime = System.currentTimeMillis() - timestamp;
            return elapsedTime < durationInSeconds * 1000L;
        }
        return false;
    }

    public void markAsProcessed(String key) {
        processedRequests.put(key, System.currentTimeMillis());
    }

    public String generateKey(PaymentRequestDTO requestDTO) {
        return requestDTO.value() + "-" + requestDTO.dueDate() + "-" + requestDTO.customer().cpfCnpj();
    }
}
