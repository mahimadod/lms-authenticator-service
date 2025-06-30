package com.example.authenticator_service.service;

import com.example.authenticator_service.dtos.RoleDTO;
import java.util.List;

public interface RoleService {
    String createRole(RoleDTO roleDTO);
    List<RoleDTO> getAllRoles();
}
