package com.example.authenticator_service.repository;

import com.example.authenticator_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    List<User> findAll();

    User findByUsername(String userName);
}
