package com.christian.time_connect.repositories;

import com.christian.time_connect.entities.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE PostEntity p SET p.description = ?1 WHERE p.id = ?2")
    void updatePostDescriptionById(String description, Long id);
}
