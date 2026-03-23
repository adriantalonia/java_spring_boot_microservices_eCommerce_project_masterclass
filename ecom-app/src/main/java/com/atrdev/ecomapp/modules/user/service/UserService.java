package com.atrdev.ecomapp.modules.user.service;

import com.atrdev.ecomapp.modules.user.dto.UserRequest;
import com.atrdev.ecomapp.modules.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> fetchAllUsers();
    UserResponse createUser(UserRequest user);
    UserResponse fetchUserById(Long id);
    UserResponse updateUser(Long id, UserRequest user);
}
