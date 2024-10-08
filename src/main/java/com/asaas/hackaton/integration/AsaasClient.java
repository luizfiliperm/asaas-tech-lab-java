package com.asaas.hackaton.integration;

import com.asaas.hackaton.dto.BalanceResponseDTO;
import com.asaas.hackaton.dto.CustomerAccountRequestDTO;
import com.asaas.hackaton.dto.ListPaymentResponseDTO;
import com.asaas.hackaton.dto.PaymentResponseDTO;
import com.asaas.hackaton.dto.transfer.TransferRequestDTO;
import com.asaas.hackaton.dto.transfer.TransferResponseDTO;
import com.asaas.hackaton.integration.dto.AsaasCreatePaymentRequestDTO;
import com.asaas.hackaton.integration.dto.AsaasCreateCustomerAccountResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

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

    public PaymentResponseDTO createPayment(AsaasCreatePaymentRequestDTO paymentRequest) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/payments")
                .toUriString();

        HttpEntity<AsaasCreatePaymentRequestDTO> entity = new HttpEntity<>(paymentRequest, createHeaders());

        ResponseEntity<PaymentResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, PaymentResponseDTO.class);

        return response.getBody();
    }

    public String createCustomer(CustomerAccountRequestDTO customerAccountDTO) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/customers")
                .toUriString();

        HttpEntity<CustomerAccountRequestDTO> entity = new HttpEntity<>(customerAccountDTO, createHeaders());

        ResponseEntity<AsaasCreateCustomerAccountResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, AsaasCreateCustomerAccountResponseDTO.class);

        return response.getBody().id();
    }

    public ListPaymentResponseDTO listPayment() {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/payments")
                .toUriString();

        HttpEntity<Void> entity = new HttpEntity<>(createHeaders());

        ResponseEntity<ListPaymentResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ListPaymentResponseDTO.class);

        return response.getBody();
    }

    public PaymentResponseDTO findPaymentById(String id) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/payments/{id}")
                .build(id)
                .toString();

        HttpEntity<Void> entity = new HttpEntity<>(createHeaders());

        ResponseEntity<PaymentResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, PaymentResponseDTO.class);

        return response.getBody();
    }

    public BigDecimal retrieveFineBalance(){
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/finance/balance")
                .toUriString();

        HttpEntity<Void> entity = new HttpEntity<>(createHeaders());

        ResponseEntity<BalanceResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, BalanceResponseDTO.class);

        return response.getBody().balance();
    }

    public TransferResponseDTO createTransfer(TransferRequestDTO transferDTO) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/transfers")
                .toUriString();

        HttpEntity<TransferRequestDTO> entity = new HttpEntity<>(transferDTO, createHeaders());

        ResponseEntity<TransferResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, TransferResponseDTO.class);

        return response.getBody();
    }
}
