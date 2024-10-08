package com.christian.timetalk_api.repositories;

import com.christian.timetalk_api.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
