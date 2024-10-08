package com.christian.timetalk_api.repositories;

import com.christian.timetalk_api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(String email);

    boolean existsByEmail(String email);
}
