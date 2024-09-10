package com.asaas.hackaton.adapter;

import java.math.BigDecimal;
import java.util.Date;

public record PaymentAdapter(
        BigDecimal value,
        String description,
        Date dueDate
) {
}
