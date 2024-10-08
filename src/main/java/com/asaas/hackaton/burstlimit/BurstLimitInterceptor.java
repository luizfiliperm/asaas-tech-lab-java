package com.asaas.hackaton.burstlimit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Component
public class BurstLimitInterceptor implements HandlerInterceptor {

    private final ConcurrentHashMap<String, Semaphore> semaphoreMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            BurstLimit burstLimit = handlerMethod.getMethodAnnotation(BurstLimit.class);

            if (burstLimit != null) {
                String key = request.getMethod() + request.getRequestURI();
                int maxParallelRequests = burstLimit.maxParallelRequests();

                Semaphore semaphore = semaphoreMap.computeIfAbsent(key, k -> new Semaphore(maxParallelRequests));
                boolean acquired = semaphore.tryAcquire();
                if (!acquired) {
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    return false;
                }
                request.setAttribute("burstLimit", semaphore);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Semaphore semaphore = (Semaphore) request.getAttribute("burstLimit");
        if (semaphore != null) {
            semaphore.release();
        }

    }

}
