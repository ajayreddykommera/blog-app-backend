package com.ajay.blog_app.dto.response;

import com.ajay.blog_app.models.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponse {
    private Long userId;
    private String fullName;
    private String userName;
    private String email;
    private String phone;
    private Set<Role> roles = new HashSet<>();
}
