package com.christian.time_connect.repositories;

import com.christian.time_connect.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
