package com.songguesser.backend.model.repository;

import com.songguesser.backend.model.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByKeycloakId(String keycloakId);
    Optional<User> findByKeycloakId(String keycloakId);

}
