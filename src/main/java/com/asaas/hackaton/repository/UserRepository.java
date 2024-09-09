package com.asaas.hackaton.repository;

import com.asaas.hackaton.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
