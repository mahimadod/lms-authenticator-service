package com.example.authenticator_service.adapter;

import com.example.authenticator_service.dtos.AuthRequest;
import com.example.authenticator_service.entity.User;

public interface AuthAdapter {
    User convertToUser(AuthRequest request);
}
