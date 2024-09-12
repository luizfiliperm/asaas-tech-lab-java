package com.asaas.hackaton.integration;

import com.asaas.hackaton.dto.CustomerAccountRequestDTO;
import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.dto.PaymentResponseDTO;
import com.asaas.hackaton.integration.dto.CreateCustomerAccountResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AsaasClient {

    private final RestTemplate restTemplate;

    @Value("${asaas.api.base-url}")
    private String baseUrl;

    @Value("${asaas.api.key}")
    private String apiKey;

    public AsaasClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("access_token", apiKey);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequest) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/payments")
                .toUriString();

        HttpEntity<PaymentRequestDTO> entity = new HttpEntity<>(paymentRequest, createHeaders());

        ResponseEntity<PaymentResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, PaymentResponseDTO.class);

        return response.getBody();
    }

    public String createCustomer(CustomerAccountRequestDTO customerAccountDTO) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/customers")
                .toUriString();

        HttpEntity<CustomerAccountRequestDTO> entity = new HttpEntity<>(customerAccountDTO, createHeaders());

        ResponseEntity<CreateCustomerAccountResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, CreateCustomerAccountResponseDTO.class);

        return response.getBody().id();
    }
}
