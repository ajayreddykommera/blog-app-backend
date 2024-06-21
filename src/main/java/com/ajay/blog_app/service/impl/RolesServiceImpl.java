package com.ajay.blog_app.service.impl;

import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.models.Role;
import com.ajay.blog_app.repositories.RoleRepository;
import com.ajay.blog_app.service.RolesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public MessageResponse addRole(Role request) {
        MessageResponse messageResponse = new MessageResponse();
        String roleName = request.getRoleName().toUpperCase();
        if (roleRepository.existsByRoleName(roleName)) {
            messageResponse.setMessage("Error: Role is already taken!");
            return messageResponse;
        }
        Role role = new Role();
        role.setRoleName(roleName);
        Role response = roleRepository.save(role);
        messageResponse.setMessage("Role [" + response.getRoleName() + "]  Added Successfully");
        return messageResponse;
    }
}
