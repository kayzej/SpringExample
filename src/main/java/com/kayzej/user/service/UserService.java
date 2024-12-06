package com.kayzej.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kayzej.user.dto.UserDto;
import com.kayzej.user.model.Users;
import com.kayzej.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public UserDto createUser(UserDto UserDto) {
        // Map the request DTO to the entity
        Users user = new Users();
        user.setFirstName(UserDto.getFirstName());
        user.setLastName(UserDto.getLastName());
        user.setEmail(UserDto.getEmail());

        // Save the user to the database
        Users savedUser = userRepository.save(user);

        // Map the saved entity to the response DTO
        return mapToResponseDto(savedUser);
    }

    // Get a user by ID
    public UserDto getUserById(Long userId) {
        // Find the user in the database
        Optional<Users> userOptional = userRepository.findById(userId);

        // Throw an exception if the user is not found
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // Map the entity to the response DTO
        return mapToResponseDto(userOptional.get());
    }

    // Update an existing user
    public UserDto updateUser(Long userId, UserDto UserDto) {
        // Find the user in the database
        Optional<Users> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // Update the user's details
        Users user = userOptional.get();
        user.setFirstName(UserDto.getFirstName());
        user.setLastName(UserDto.getLastName());
        user.setEmail(UserDto.getEmail());

        // Save the updated user to the database
        Users updatedUser = userRepository.save(user);

        // Map the updated entity to the response DTO
        return mapToResponseDto(updatedUser);
    }

    // Delete a user by ID
    public void deleteUser(Long userId) {
        // Check if the user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // Delete the user
        userRepository.deleteById(userId);
    }

    // Helper method to map a User entity to a UserDto
    private UserDto mapToResponseDto(Users users) {
        UserDto responseDto = new UserDto();
        responseDto.setId(users.getId());
        responseDto.setFirstName(users.getFirstName());
        responseDto.setLastName(users.getLastName());
        responseDto.setEmail(users.getEmail());
        return responseDto;
    }
}