package com.example.authenticator_service.repository;

import com.example.authenticator_service.entity.Role;
import com.example.authenticator_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String userName);
    boolean existsByName(String name);
}
