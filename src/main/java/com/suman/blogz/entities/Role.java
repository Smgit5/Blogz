package com.suman.blogz.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<MyUser> users = new HashSet<>();
}
