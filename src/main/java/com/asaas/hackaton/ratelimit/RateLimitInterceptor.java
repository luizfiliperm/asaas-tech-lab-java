package com.asaas.hackaton.ratelimit;

import com.asaas.hackaton.controller.AuthenticatorInterceptor;
import com.asaas.hackaton.service.RateLimitService;
import com.asaas.hackaton.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimitService rateLimitService;

    public RateLimitInterceptor(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RateLimit rateLimit = handlerMethod.getMethodAnnotation(RateLimit.class);

            if (rateLimit != null) {
                String userIssuer = JwtUtils.getIssuer(request.getHeader(AuthenticatorInterceptor.HEADER_AUTHORIZATION));
                int maxRequestsPerSecond = rateLimit.requestsPerMinute();

                if (!rateLimitService.isAllowed(userIssuer, request, maxRequestsPerSecond)) {
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    return false;
                }
            }
        }
        return true;
    }
}
