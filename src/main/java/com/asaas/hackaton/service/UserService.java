package com.asaas.hackaton.service;

import com.asaas.hackaton.domain.user.User;
import com.asaas.hackaton.dto.UserRequestDTO;
import com.asaas.hackaton.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.email());
        return userRepository.save(user);
    }
}
