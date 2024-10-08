package com.asaas.hackaton.idempotency;

import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.dto.PaymentResponseDTO;
import com.asaas.hackaton.service.PaymentIdempotencyService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PaymentIdempotencyAspect {

    private final PaymentIdempotencyService idempotencyService;

    public PaymentIdempotencyAspect(PaymentIdempotencyService idempotencyService) {
        this.idempotencyService = idempotencyService;
    }

    @Around("@annotation(idempotent)")
    public Object checkIdempotency(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String idempotencyKey = request.getHeader("Idempotency-Key");

        if (idempotencyKey == null) {
            return joinPoint.proceed();
        }

        PaymentResponseDTO cachedResponse = idempotencyService.findPaymentResponseByKey(idempotencyKey);
        if (cachedResponse != null) {
            return ResponseEntity.ok(cachedResponse);
        }

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof PaymentRequestDTO) {
            Object result = joinPoint.proceed();

            if (result instanceof ResponseEntity) {
                ResponseEntity<PaymentResponseDTO> responseEntity = (ResponseEntity<PaymentResponseDTO>) result;
                if (responseEntity.getBody() != null) {
                    idempotencyService.markAsProcessed(idempotencyKey, responseEntity.getBody());
                }
            }

            return result;
        }

        return joinPoint.proceed();
    }

}
