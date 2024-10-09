package com.asaas.hackaton.service;

import com.asaas.hackaton.limit.BaseLimitService;
import com.asaas.hackaton.ratelimit.RequestInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RateLimitService implements BaseLimitService {

    private final ConcurrentHashMap<String, RequestInfo> requestInfoMap = new ConcurrentHashMap<>();

    private final Integer MAX_SECONDS = 60;

    public Boolean isAllowed(String userIp, HttpServletRequest request, int maxRequestsPerSecond) {
        String key = buildKey(userIp, request.getMethod(), request.getRequestURI());
        RequestInfo requestInfo = requestInfoMap.get(key);
        if (requestInfo == null) {
            requestInfo = new RequestInfo(Instant.now(), new AtomicInteger());
            requestInfoMap.putIfAbsent(key, requestInfo);
        }

        if (requestInfo.getInstant().plusSeconds(MAX_SECONDS).isBefore(Instant.now())) {
            requestInfo.setRequestCount(new AtomicInteger());
            requestInfo.setInstant(Instant.now());
            return true;
        }

        if (requestInfo.getRequestCount().get() < maxRequestsPerSecond) {
            requestInfo.incrementRequestCount();
            return true;
        }

        return false;
    }

    private String buildKey(String userIssuer, String method, String uri) {
        return userIssuer + method + uri;
    }

    @Override
    public void reset() {
        requestInfoMap.clear();
    }
}
