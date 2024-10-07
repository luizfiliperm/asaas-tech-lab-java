package com.asaas.hackaton.controller;

import com.asaas.hackaton.repository.UserRepository;
import com.asaas.hackaton.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticatorFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthenticatorFilter(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader(HEADER_AUTHORIZATION) == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        String issuer = userService.getIssuer(request.getHeader(HEADER_AUTHORIZATION));
        if (issuer == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        request.setAttribute("user", userRepository.findByEmail(issuer));
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        if (path.startsWith("/healthcheck")) return true;
        if (!path.startsWith("/payments") && !path.startsWith("/users")) return true;

        return false;
    }
}