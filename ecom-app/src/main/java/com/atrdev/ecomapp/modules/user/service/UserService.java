package com.atrdev.ecomapp.modules.user.service;

import com.atrdev.ecomapp.modules.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> fetchAllUsers();
    User createUser(User user);
    User fetchUserById(Long id);
    User updateUser(Long id, User user);
}
