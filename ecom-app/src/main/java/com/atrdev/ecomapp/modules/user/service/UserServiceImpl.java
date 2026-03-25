package com.atrdev.ecomapp.modules.user.service;

import com.atrdev.ecomapp.modules.user.dto.AddressDTO;
import com.atrdev.ecomapp.modules.user.dto.UserRequest;
import com.atrdev.ecomapp.modules.user.dto.UserResponse;
import com.atrdev.ecomapp.modules.user.entity.Address;
import com.atrdev.ecomapp.modules.user.entity.User;
import com.atrdev.ecomapp.shared.exception.ResourceNotFoundException;
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
    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    @Transactional
    @Override
    public UserResponse createUser(UserRequest user) {
        User userEntity = userRepository.save(mapToUser(user));
        return mapToUserResponse(userEntity);
    }

    @Override
    public UserResponse fetchUserById(Long id) {
        /*return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User", id));*/
        return userRepository
                .findById(id)
                .map(this::mapToUserResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest updatedUser) {
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
                    userRepository.save(mapToExistingUser(existingUser, updatedUser));
                    return existingUser;
                }).map(this::mapToUserResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZip(user.getAddress().getZip());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;
    }

    private User mapToUser(UserRequest user) {
        User userEntity = new User();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        if (user.getAddress() != null) {
            Address addressEntity = new Address();
            addressEntity.setStreet(user.getAddress().getStreet());
            addressEntity.setCity(user.getAddress().getCity());
            addressEntity.setState(user.getAddress().getState());
            addressEntity.setCountry(user.getAddress().getCountry());
            addressEntity.setZip(user.getAddress().getZip());
            userEntity.setAddress(addressEntity);
        }
        return userEntity;
    }

    private User mapToExistingUser(User existingUser, UserRequest userUpdated) {
        existingUser.setFirstName(userUpdated.getFirstName());
        existingUser.setLastName(userUpdated.getLastName());
        existingUser.setEmail(userUpdated.getEmail());
        existingUser.setPhone(userUpdated.getPhone());
        if (userUpdated.getAddress() != null) {
            existingUser.getAddress().setStreet(userUpdated.getAddress().getStreet());
            existingUser.getAddress().setCity(userUpdated.getAddress().getCity());
            existingUser.getAddress().setState(userUpdated.getAddress().getState());
            existingUser.getAddress().setCountry(userUpdated.getAddress().getCountry());
            existingUser.getAddress().setZip(userUpdated.getAddress().getZip());
        }
        return existingUser;
    }
}
