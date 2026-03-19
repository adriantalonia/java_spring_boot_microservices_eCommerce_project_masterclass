package com.atrdev.ecomapp.modules.user.service;

import com.atrdev.ecomapp.modules.user.entity.User;
import com.atrdev.ecomapp.modules.user.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<User> fetchAllUsers() {
        return userList;
    }

    @Override
    public List<User> addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
        return userList;
    }

    @Override
    public User fetchUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return existingUser;
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
}
