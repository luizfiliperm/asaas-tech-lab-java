package com.asaas.hackaton.service;

import com.asaas.hackaton.domain.user.User;
import com.asaas.hackaton.limit.BaseLimitService;
import com.asaas.hackaton.quotalimit.UserQuota;
import com.asaas.hackaton.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class QuotaLimitService implements BaseLimitService {

    private final UserRepository userRepository;

    private final Map<Long, UserQuota> userQuotaMap = new HashMap<>();

    public QuotaLimitService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isAllowed(String userEmail) {
        LocalDate today = LocalDate.now();
        User user = userRepository.findByEmail(userEmail);
        UserQuota userQuota = userQuotaMap.get(user.getId());

        if (userQuota == null || !userQuota.getDate().equals(today)) {
            userQuota = new UserQuota(today, 1);
            userQuotaMap.put(user.getId(), userQuota);
        }

        if (userQuota.getRequestCount() >= user.getMaxPaymentsCreatedPerDay()) {
            return false;
        }

        userQuota.incrementRequestCount();
        return true;
    }

    @Override
    public void reset() {
        userQuotaMap.clear();
    }
}
