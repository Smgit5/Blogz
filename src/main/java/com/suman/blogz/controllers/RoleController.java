package com.suman.blogz.controllers;

import com.suman.blogz.entities.Role;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.RoleDto;
import com.suman.blogz.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/blogz/admin/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse> createRole(@Valid @RequestBody Role newRole) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(newRole));
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }
}
