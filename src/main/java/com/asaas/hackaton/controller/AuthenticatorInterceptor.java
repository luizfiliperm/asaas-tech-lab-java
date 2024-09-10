package com.asaas.hackaton.controller;

import com.asaas.hackaton.repository.UserRepository;
import com.asaas.hackaton.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticatorInterceptor implements HandlerInterceptor {

    private static final String HEADER_EMAIL = "Email";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    private final UserService userService;

    public AuthenticatorInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader(HEADER_AUTHORIZATION) == null) {
            response.addHeader("Interceptor", "Authorization não enviado");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        userService.validateLogin(request.getHeader(HEADER_AUTHORIZATION));
        return true;
    }

}