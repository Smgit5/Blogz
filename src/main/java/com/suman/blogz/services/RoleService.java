package com.suman.blogz.services;

import com.suman.blogz.entities.Role;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.RoleDto;

import java.util.List;

public interface RoleService {
    ApiResponse createRole(Role newRole);
    List<RoleDto> getAllRoles();
}
