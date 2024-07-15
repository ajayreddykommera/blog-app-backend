package com.ajay.blog_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Pattern(regexp = "(?i)ROLE_[A-Z]+", message = "Invalid role, It should be like eg: ROLE_NAME")
    @Column(nullable = false, unique = true)
    private String roleName;
}
