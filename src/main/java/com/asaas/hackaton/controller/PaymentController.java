package com.asaas.hackaton.controller;

import com.asaas.hackaton.adapter.PaymentAdapter;
import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.dto.PaymentResponseDTO;
import com.asaas.hackaton.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> save(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO responseDTO = paymentService.create(paymentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
