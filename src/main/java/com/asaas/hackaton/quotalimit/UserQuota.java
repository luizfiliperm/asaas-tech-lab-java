package com.asaas.hackaton.quotalimit;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class UserQuota {

    private LocalDate date;
    private AtomicInteger requestCount;

    public UserQuota(LocalDate date, AtomicInteger requestCount) {
        this.date = date;
        this.requestCount = requestCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public AtomicInteger getRequestCount() {
        return requestCount;
    }

    public void incrementRequestCount() {
        requestCount.incrementAndGet();
    }
}