package com.christian.timetalk_api.repositories;

import com.christian.timetalk_api.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
