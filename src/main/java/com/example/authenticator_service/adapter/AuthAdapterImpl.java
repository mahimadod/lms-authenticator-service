package com.example.authenticator_service.adapter;

import com.example.authenticator_service.dtos.AuthRequest;
import com.example.authenticator_service.entity.Role;
import com.example.authenticator_service.entity.User;
import com.example.authenticator_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthAdapterImpl implements AuthAdapter {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User convertToUser(AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        Optional<String> roleName = request.getRole().stream().findFirst();

        roleName.ifPresent(name -> {
            Role role = roleRepository.findByName(name);
            user.setRole(role);
        });

        return user;
    }
}
