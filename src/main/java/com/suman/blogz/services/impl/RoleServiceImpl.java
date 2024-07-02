package com.suman.blogz.services.impl;

import com.suman.blogz.repository.RoleRepository;
import com.suman.blogz.entities.Role;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.RoleDto;
import com.suman.blogz.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse createRole(Role newRole) {
        newRole.setRoleName(newRole.getRoleName().toUpperCase());
        roleRepository.save(newRole);
        return new ApiResponse("Role successfully created", true);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .toList();
    }
}
