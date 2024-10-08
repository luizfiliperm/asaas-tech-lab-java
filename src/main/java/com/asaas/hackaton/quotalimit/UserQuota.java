package com.asaas.hackaton.quotalimit;

import java.time.LocalDate;

public class UserQuota {

    private LocalDate date;
    private int requestCount;

    public UserQuota(LocalDate date, int requestCount) {
        this.date = date;
        this.requestCount = requestCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }
}