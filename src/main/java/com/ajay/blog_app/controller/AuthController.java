package com.ajay.blog_app.controller;

import com.ajay.blog_app.dto.request.LoginRequest;
import com.ajay.blog_app.dto.request.SignupRequest;
import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.dto.response.SigninResponse;
import com.ajay.blog_app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("request to client {}", loginRequest);
        SigninResponse response = authService.authenticateUser(loginRequest);
        if (response.getCode() == 1) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        log.info("request to client {}", signUpRequest);
        MessageResponse response = authService.registerUser(signUpRequest);
        log.info("response to client {}", response);
        if (response.getCode() == 1) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

}
