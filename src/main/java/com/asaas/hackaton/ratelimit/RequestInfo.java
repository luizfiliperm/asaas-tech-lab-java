package com.asaas.hackaton.ratelimit;

import java.time.Instant;

public class RequestInfo {

    private Instant instant;

    private Integer requestCount;

    public RequestInfo() {
    }

    public RequestInfo(Instant instant, Integer requestCount) {
        this.instant = instant;
        this.requestCount = requestCount;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }
}
