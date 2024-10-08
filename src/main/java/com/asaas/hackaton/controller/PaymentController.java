package com.asaas.hackaton.controller;

import com.asaas.hackaton.adapter.PaymentAdapter;
import com.asaas.hackaton.burstlimit.BurstLimit;
import com.asaas.hackaton.dto.ListPaymentResponseDTO;
import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.dto.PaymentResponseDTO;
import com.asaas.hackaton.idempotency.Idempotent;
import com.asaas.hackaton.quotalimit.QuotaLimit;
import com.asaas.hackaton.ratelimit.RateLimit;
import com.asaas.hackaton.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @QuotaLimit
    @BurstLimit(maxParallelRequests = 10)
    @Idempotent
    @Operation(description = "https://docs.asaas.com/reference/criar-nova-cobranca")
    public ResponseEntity<PaymentResponseDTO> save(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO responseDTO = paymentService.create(paymentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @RateLimit(requestsPerMinute = 100)
    public ResponseEntity<ListPaymentResponseDTO> list() {
        ListPaymentResponseDTO responseDTO = paymentService.list();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findById(@PathVariable String id) {
        PaymentResponseDTO responseDTO = paymentService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }
}
