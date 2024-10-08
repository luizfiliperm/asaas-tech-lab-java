package com.asaas.hackaton.controller;

import com.asaas.hackaton.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticatorInterceptor implements HandlerInterceptor {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BYPASS_URI = "/healthcheck";
    private final UserService userService;

    public AuthenticatorInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals(BYPASS_URI)) return true;

        if (request.getHeader(HEADER_AUTHORIZATION) == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        Boolean isValid = userService.validateLogin(request.getHeader(HEADER_AUTHORIZATION));
        if (!isValid) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }

}