package com.example.authenticator_service.service;

import com.example.authenticator_service.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    String assignRoleToUser(Long userId, Long roleId);
}
