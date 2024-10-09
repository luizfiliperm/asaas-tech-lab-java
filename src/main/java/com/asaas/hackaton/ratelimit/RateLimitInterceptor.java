package com.asaas.hackaton.ratelimit;

import com.asaas.hackaton.controller.AuthenticatorFilter;
import com.asaas.hackaton.service.RateLimitService;
import com.asaas.hackaton.util.JwtUtils;
import com.asaas.hackaton.util.UserIpUtils;
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
                String userIp = UserIpUtils.getUserIp(request);
                int maxRequestsPerMinute = rateLimit.requestsPerMinute();

                if (!rateLimitService.isAllowed(userIp, request, maxRequestsPerMinute)) {
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    return false;
                }
            }
        }
        return true;
    }
}
