package com.asaas.hackaton.service;

import com.asaas.hackaton.dto.UserRequestDTO;
import com.asaas.hackaton.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetupService {

    private final UserService userService;
    private final UserRepository userRepository;

    public SetupService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void setup() {
        final Long firstId = 1L;
        boolean done = userRepository.existsById(firstId);
        if (done) return;

        System.out.println(this.getClass().getSimpleName() + " >> Setting up database");

        UserRequestDTO userRequestDTO = new UserRequestDTO("user1@example.com", "d10f4c6c-56bd-4a6d-b5ed-75dc39805045");
        userService.save(userRequestDTO);

        userRequestDTO = new UserRequestDTO("user2@example.com", "13adcbb7-a879-4a34-a2bf-1b6acbbd7fac");
        userService.save(userRequestDTO);

        userRequestDTO = new UserRequestDTO("user3@example.com", "3918db97-6a20-48e6-b440-b09d6f91714a");
        userService.save(userRequestDTO);

        userRequestDTO = new UserRequestDTO("user4@example.com", "168a5b37-85f5-4443-a920-302a4a32a673");
        userService.save(userRequestDTO);

        userRequestDTO = new UserRequestDTO("user5@example.com", "30d3dcf3-4890-476f-a2cf-f6132f059293");
        userService.save(userRequestDTO);
    }
}
