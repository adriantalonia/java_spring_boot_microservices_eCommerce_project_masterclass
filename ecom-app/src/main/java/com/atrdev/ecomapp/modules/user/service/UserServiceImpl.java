package com.atrdev.ecomapp.modules.user.service;

import com.atrdev.ecomapp.modules.user.entity.User;
import com.atrdev.ecomapp.modules.user.exception.ResourceNotFoundException;
import com.atrdev.ecomapp.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User fetchUserById(Long id) {
        /*return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User", id));*/
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
       /* return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return existingUser;
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", id));*/
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
                    return existingUser;
                })
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
}
