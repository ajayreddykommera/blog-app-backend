package com.ajay.blog_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String userName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String password;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String phone;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String fullName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
