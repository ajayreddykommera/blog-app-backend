package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.request.LoginRequest;
import com.ajay.blog_app.dto.request.SignupRequest;
import com.ajay.blog_app.dto.response.JwtResponse;
import com.ajay.blog_app.dto.response.MessageResponse;

public interface  AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    MessageResponse registerUser(SignupRequest signUpRequest);
}
