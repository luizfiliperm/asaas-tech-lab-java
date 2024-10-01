package com.asaas.hackaton.idempotency;

import com.asaas.hackaton.dto.PaymentRequestDTO;
import com.asaas.hackaton.exception.BusinessException;
import com.asaas.hackaton.service.PaymentIdempotencyService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaymentIdempotencyAspect {

    private final PaymentIdempotencyService idempotencyService;

    public PaymentIdempotencyAspect(PaymentIdempotencyService idempotencyService) {
        this.idempotencyService = idempotencyService;
    }

    @Around("@annotation(idempotent)")
    public Object checkIdempotency(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof PaymentRequestDTO) {
            PaymentRequestDTO requestDTO = (PaymentRequestDTO) args[0];

            String key = idempotencyService.generateKey(requestDTO);

            if (idempotencyService.isRequestProcessed(key, idempotent.durationInSeconds())) {
                throw new BusinessException("idempotency error", "Payment request already processed.");
            }

            Object result = joinPoint.proceed();

            idempotencyService.markAsProcessed(key);

            return result;
        }

        return joinPoint.proceed();
    }

}
