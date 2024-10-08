package com.christian.timetalk_api.repositories;

import com.christian.timetalk_api.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
