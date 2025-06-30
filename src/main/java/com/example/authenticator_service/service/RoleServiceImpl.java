package com.example.authenticator_service.service;


import com.example.authenticator_service.dtos.RoleDTO;
import com.example.authenticator_service.entity.Role;
import com.example.authenticator_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String createRole(RoleDTO roleDTO) {
        if (roleRepository.existsByName(roleDTO.getName())) {
            return "Role already exists.";
        }

        Role role = new Role();
        role.setName(roleDTO.getName());
        roleRepository.save(role);

        return "Role created successfully.";
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(role -> {
            RoleDTO dto = new RoleDTO();
            dto.setId(role.getId());
            dto.setName(role.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
