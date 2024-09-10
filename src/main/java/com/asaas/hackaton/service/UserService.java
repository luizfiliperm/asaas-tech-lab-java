package com.asaas.hackaton.service;

import com.asaas.hackaton.adapter.UserAdapter;
import com.asaas.hackaton.domain.user.User;
import com.asaas.hackaton.dto.UserRequestDTO;
import com.asaas.hackaton.repository.UserRepository;
import com.asaas.hackaton.util.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserAdapter save(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.email());

        user.setApiKey(JwtUtils.generateToken(userRequestDTO.email()));
        user = userRepository.save(user);

        return new UserAdapter(user.getEmail(), user.getApiKey());
    }

    public Boolean validateLogin(String apiKey) {
        return JwtUtils.getIssuer(apiKey) != null;
    }
}
