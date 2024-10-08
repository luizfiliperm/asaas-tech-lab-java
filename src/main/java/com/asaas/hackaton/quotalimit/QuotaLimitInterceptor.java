package com.asaas.hackaton.quotalimit;

import com.asaas.hackaton.controller.AuthenticatorFilter;
import com.asaas.hackaton.service.QuotaLimitService;
import com.asaas.hackaton.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class QuotaLimitInterceptor implements HandlerInterceptor {

    private final QuotaLimitService quotaLimitService;

    public QuotaLimitInterceptor(QuotaLimitService quotaLimitService) {
        this.quotaLimitService = quotaLimitService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            QuotaLimit quotaLimit = handlerMethod.getMethodAnnotation(QuotaLimit.class);

            if (quotaLimit != null) {
                String userIssuer = JwtUtils.getIssuer(request.getHeader(AuthenticatorFilter.HEADER_AUTHORIZATION));

                if (!quotaLimitService.isAllowed(userIssuer)) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    return false;
                }
            }
        }

        return true;
    }
}