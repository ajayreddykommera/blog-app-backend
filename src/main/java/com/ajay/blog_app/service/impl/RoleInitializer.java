//package com.ajay.blog_app.service.impl;
//
//import com.ajay.blog_app.models.Role;
//import com.ajay.blog_app.repositories.RoleRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class RoleInitializer {
//
//    private final RoleRepository roleRepository;
//
//    @PostConstruct
//    public void init() {
//        String[] roles = {"ROLE_AUTHOR", "ROLE_NORMALUSER", "ROLE_ADMIN"};
//        List<Role> roleList=new LinkedList<>();
//        for (String role : roles) {
//            Role roleObj = new Role();
//            roleObj.setRoleName(role);
//            roleList.add(roleObj);
//        }
//        roleRepository.saveAll(roleList);
//    }
//}
//
