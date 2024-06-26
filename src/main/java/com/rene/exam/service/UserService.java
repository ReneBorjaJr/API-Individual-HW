package com.rene.exam.service;

import com.rene.exam.domain.User;
import com.rene.exam.dto.user.UserRequest;
import com.rene.exam.dto.user.UserResponse;
import com.rene.exam.exceptionhandler.GlobalExceptionHandler;
import com.rene.exam.exceptions.ResourceNotFoundException;
import com.rene.exam.repository.UserRepository;
import com.rene.exam.successfulresponse.ApiResponse;
import com.rene.exam.successfulresponse.DeleteSuccessful;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        logger.info("Created a new user with username: {}", userRequest.getUsername());
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setGender(userRequest.getGender());
        return new UserResponse(userRepository.save(user));
    }

    public UserResponse getUserById(Long userId) {
        UserResponse userResponse = new UserResponse(checkUserExists(userId));
        logger.info("Fetching user with id: {}", userId);
        return userResponse;

    }

    public List<UserResponse> getAllUsers() {
        List<User> allUsers = userRepository.findAllUsers();
        if (allUsers.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        logger.info("Fetching all users");
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : allUsers) {
            userResponses.add(new UserResponse(user));
        }
        return userResponses;
    }

    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User oldUser = checkUserExists(userId);
        oldUser.setUsername(userRequest.getUsername());
        oldUser.setGender(userRequest.getGender());
        User updatedUser = userRepository.save(oldUser);
        UserResponse userResponse = new UserResponse(updatedUser);
        logger.info("Updated user with id: {}", userId);
        return userResponse;
    }

    public DeleteSuccessful deleteUser(Long userId) {
        User user = checkUserExists(userId);
        userRepository.delete(user);
        logger.info("Deleted user with id: {}", userId);
        return new DeleteSuccessful(HttpStatus.OK.value(), "User with id: " + userId + " successfully deleted");

    }

    private User checkUserExists(Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User with id: " + userId + " does not exist");
        }
        return user;
    }
}
