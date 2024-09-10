package com.asaas.hackaton.service;

import com.asaas.hackaton.adapter.PaymentAdapter;
import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.util.CustomDateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
public class PaymentService {

    public PaymentAdapter create(PaymentRequestDTO paymentRequestDTO) {
        Date dueDate = CustomDateUtils.parseDate(paymentRequestDTO.dueDate());

        // TODO: Integracao Asaas

        return new PaymentAdapter(paymentRequestDTO.value(), paymentRequestDTO.description(), dueDate);
    }
}
