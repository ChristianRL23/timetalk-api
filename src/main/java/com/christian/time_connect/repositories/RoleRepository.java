package com.christian.time_connect.repositories;

import com.christian.time_connect.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
