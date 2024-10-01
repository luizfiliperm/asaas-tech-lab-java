package com.asaas.hackaton.config;

import com.asaas.hackaton.burstlimit.BurstLimitInterceptor;
import com.asaas.hackaton.controller.AuthenticatorInterceptor;
import com.asaas.hackaton.ratelimit.RateLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    private final AuthenticatorInterceptor authenticatorInterceptor;

    private final BurstLimitInterceptor burstLimitInterceptor;

    private final RateLimitInterceptor rateLimitInterceptor;

    public ConfigInterceptor(AuthenticatorInterceptor authenticatorInterceptor, BurstLimitInterceptor burstLimitInterceptor ,RateLimitInterceptor rateLimitInterceptor) {
        this.authenticatorInterceptor = authenticatorInterceptor;
        this.burstLimitInterceptor = burstLimitInterceptor;
        this.rateLimitInterceptor = rateLimitInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticatorInterceptor);
        registry.addInterceptor(burstLimitInterceptor);
        registry.addInterceptor(rateLimitInterceptor);
    }

}