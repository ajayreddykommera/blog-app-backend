package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUser(Long userId);

    List<UserResponse> getAllUsers();
}
