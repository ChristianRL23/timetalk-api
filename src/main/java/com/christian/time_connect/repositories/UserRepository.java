package com.christian.time_connect.repositories;

import com.christian.time_connect.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(String email);

    boolean existsByEmail(String email);
}
