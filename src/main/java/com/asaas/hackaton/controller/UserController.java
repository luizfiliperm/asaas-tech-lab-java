package com.asaas.hackaton.controller;

import com.asaas.hackaton.domain.user.User;
import com.asaas.hackaton.dto.UserRequestDTO;
import com.asaas.hackaton.dto.UserResponseDTO;
import com.asaas.hackaton.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.save(userRequestDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getEmail());
        return ResponseEntity.ok(userResponseDTO);
    }
}
