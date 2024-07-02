package com.suman.blogz.payloads.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MyUserDto {
    private Integer userId;

    private String firstName;

    private String lastName;

    private String email;

    private String about;

    private Set<RoleDto> roles;
}
