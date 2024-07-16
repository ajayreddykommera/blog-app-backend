package com.ajay.blog_app.service.impl;

import com.ajay.blog_app.dto.request.LoginRequest;
import com.ajay.blog_app.dto.request.SignupRequest;
import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.dto.response.SigninResponse;
import com.ajay.blog_app.models.Role;
import com.ajay.blog_app.models.User;
import com.ajay.blog_app.repositories.RoleRepository;
import com.ajay.blog_app.repositories.UserRepository;
import com.ajay.blog_app.security.jwt.JwtUtils;
import com.ajay.blog_app.security.services.UserDetailsImpl;
import com.ajay.blog_app.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public SigninResponse authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Generate jwt token
            String jwtToken = jwtUtils.generateJwtToken(authentication);
            log.info("jwtToken generated...");
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            SigninResponse signinResponse = new SigninResponse();
            signinResponse.setCode(0);
            signinResponse.setMessage("Signed in Successfully");
            signinResponse.setToken(jwtToken);
            signinResponse.setUsername(userDetails.getUsername());
            signinResponse.setId(userDetails.getId());
            signinResponse.setRoles(roles);
            signinResponse.setEmail(userDetails.getEmail());
            return signinResponse;
        } catch (AuthenticationException e) {
            SigninResponse signinResponse = new SigninResponse();
            signinResponse.setCode(1);
            signinResponse.setMessage("Invalid username or password");
            return signinResponse;
        }
    }


    @Override
    public MessageResponse registerUser(SignupRequest signUpRequest) {
        MessageResponse response = new MessageResponse();
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            response.setMessage("Error: Username is already taken!");
            response.setCode(1);
            return response;
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            response.setMessage("Error: Email is already in use!");
            response.setCode(1);
            return response;
        }

        User user = new User();
        user.setUserName(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setPhone(signUpRequest.getPhone());
        user.setFullName(signUpRequest.getFullName());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByRoleName("ROLE_NORMALUSER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {


            for (String roleStr : strRoles) {
                try {
                    Role role = roleRepository.findByRoleName(roleStr.toUpperCase())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(role);

                } catch (IllegalArgumentException e) {
                    response.setMessage("Error: Role - " + roleStr + " not found.");
                    return response;
                }
            }
        }

        user.setRoles(roles);
        userRepository.save(user);
        response.setMessage("User registered successfully!");
        response.setCode(0);
        return response;
    }
}
