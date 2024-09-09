package com.asaas.hackaton.service;

import com.asaas.hackaton.assets.AccessNotPermittedException;
import com.asaas.hackaton.assets.NotFoundException;
import com.asaas.hackaton.domain.user.User;
import com.asaas.hackaton.dto.UserRequestDTO;
import com.asaas.hackaton.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder apiKeyEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder apiKeyEncoder) {
        this.userRepository = userRepository;
        this.apiKeyEncoder = apiKeyEncoder;
    }

    public User save(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.email());
        user.setApiKey(buildEncodedApiKey());
        return userRepository.save(user);
    }

    public void validateLogin(String email, String apiKey) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new NotFoundException("User not found");

        boolean isValid = apiKeyEncoder.matches(apiKey, user.getApiKey());
        if (!isValid) throw new AccessNotPermittedException("Invalid API key");
    }

    private String buildEncodedApiKey() {
        String apiKey = "1".toString();
        return apiKeyEncoder.encode(apiKey);
    }
}
