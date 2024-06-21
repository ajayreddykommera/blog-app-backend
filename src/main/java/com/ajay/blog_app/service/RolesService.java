package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.models.Role;

import java.util.List;

public interface RolesService {
    List<Role> getAllRoles();

    MessageResponse addRole(Role role);
}
