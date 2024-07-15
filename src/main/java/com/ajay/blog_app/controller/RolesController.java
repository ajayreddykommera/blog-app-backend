package com.ajay.blog_app.controller;

import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.models.Role;
import com.ajay.blog_app.service.RolesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Slf4j
public class RolesController {
    private final RolesService rolesService;

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<Role>> getAllRole() {

        List<Role> response = rolesService.getAllRoles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/addRole")
    public ResponseEntity<MessageResponse> addRole(@RequestBody @Valid Role role) {
        log.info("request from client {}", role);
        MessageResponse response = rolesService.addRole(role);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
