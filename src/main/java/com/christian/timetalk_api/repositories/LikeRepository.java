package com.christian.timetalk_api.repositories;

import com.christian.timetalk_api.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findLikeEntityByUserId(Long userId);
}
