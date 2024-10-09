package com.asaas.hackaton.ratelimit;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestInfo {

    private Instant instant;

    private AtomicInteger requestCount;

    public RequestInfo() {
    }

    public RequestInfo(Instant instant, AtomicInteger requestCount) {
        this.instant = instant;
        this.requestCount = requestCount;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public AtomicInteger getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(AtomicInteger requestCount) {
        this.requestCount = requestCount;
    }

    public void incrementRequestCount() {
        requestCount.incrementAndGet();
    }
}
