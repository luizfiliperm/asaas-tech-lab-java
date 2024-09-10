package com.asaas.hackaton.config;

import com.asaas.hackaton.controller.AuthenticatorInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ConfigInterceptor implements WebMvcConfigurer {

    private final AuthenticatorInterceptor authenticatorInterceptor;

    public ConfigInterceptor(AuthenticatorInterceptor authenticatorInterceptor) {
        this.authenticatorInterceptor = authenticatorInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticatorInterceptor);
    }

}