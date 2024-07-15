package com.ajay.blog_app.repositories;

import com.ajay.blog_app.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
    Boolean existsByRoleName(String roleName);
}
