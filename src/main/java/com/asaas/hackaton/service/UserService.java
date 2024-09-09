package com.asaas.hackaton.service;

import com.asaas.hackaton.adapter.UserAdapter;
import com.asaas.hackaton.exception.AccessNotPermittedException;
import com.asaas.hackaton.exception.NotFoundException;
import com.asaas.hackaton.domain.user.User;
import com.asaas.hackaton.dto.UserRequestDTO;
import com.asaas.hackaton.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder apiKeyEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder apiKeyEncoder) {
        this.userRepository = userRepository;
        this.apiKeyEncoder = apiKeyEncoder;
    }

    public UserAdapter save(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.email());

        String apiKey = UUID.randomUUID().toString();

        user.setApiKey(apiKeyEncoder.encode(apiKey));
        user = userRepository.save(user);

        return new UserAdapter(user.getEmail(), apiKey);
    }

    public void validateLogin(String email, String apiKey) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new NotFoundException("User not found");

        boolean isValid = apiKeyEncoder.matches(apiKey, user.getApiKey());
        if (!isValid) throw new AccessNotPermittedException("Invalid API key");
    }
}
