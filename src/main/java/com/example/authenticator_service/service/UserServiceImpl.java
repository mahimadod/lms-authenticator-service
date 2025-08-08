package com.example.authenticator_service.service;

import com.example.authenticator_service.dtos.UserDTO;
import com.example.authenticator_service.entity.Role;
import com.example.authenticator_service.entity.User;
import com.example.authenticator_service.repository.RoleRepository;
import com.example.authenticator_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND,"User not found with ID: " + id));
        return convertToDTO(user);
    }

    @Override
    public String assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND,"User not found with ID: " + userId));

        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND,"Role not found with ID: " + roleId));

        user.setRole(role);
        userRepository.save(user);
        return "Role assigned successfully to user: " + user.getUsername();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoleName(user.getRole() != null ? user.getRole().getName() : null);
        return dto;
    }
}
