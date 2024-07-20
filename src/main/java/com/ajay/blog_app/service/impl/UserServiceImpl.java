package com.ajay.blog_app.service.impl;

import com.ajay.blog_app.dto.response.UserResponse;
import com.ajay.blog_app.models.User;
import com.ajay.blog_app.repositories.UserRepository;
import com.ajay.blog_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getUser(Long userId) {
        UserResponse response = new UserResponse();
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            User user = userById.get();
            response = mapUserToUserResponse(user);
            return response;
        }
        return response;
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRoles(user.getRoles());
        return response;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponseList = new LinkedList<>();
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            UserResponse userResponse = mapUserToUserResponse(user);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }
}
