package com.ajay.blog_app.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SigninResponse {
    private int code;
    private String message;
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
