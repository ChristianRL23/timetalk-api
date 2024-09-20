package com.christian.time_connect.repositories;

import com.christian.time_connect.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
}
