package com.asaas.hackaton.controller;

import com.asaas.hackaton.exception.NotFoundException;
import com.asaas.hackaton.service.QuotaLimitService;
import com.asaas.hackaton.service.RateLimitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitController {

    private final QuotaLimitService  quotaLimitService;

    private final RateLimitService rateLimitService;

    public LimitController(QuotaLimitService quotaLimitService, RateLimitService rateLimitService) {
        this.quotaLimitService = quotaLimitService;
        this.rateLimitService = rateLimitService;
    }

    @PostMapping("/reset")
    public void reset(@RequestParam String limiterName) {
        switch (limiterName) {
            case "quota":
                quotaLimitService.reset();
                break;
            case "rate":
                rateLimitService.reset();
                break;
            default:
                throw new NotFoundException("LimiterNameNotFound");
        }

    }

}
