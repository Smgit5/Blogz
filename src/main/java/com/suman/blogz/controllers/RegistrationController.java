package com.suman.blogz.controllers;

import com.suman.blogz.payloads.request.UserRegisterRequestData;
import com.suman.blogz.payloads.response.JwtResponse;
import com.suman.blogz.services.impl.securityService.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogz")
public class RegistrationController {
    @Autowired
    private UserAuthService userAuthService;
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@Valid @ModelAttribute UserRegisterRequestData userRegisterRequestData) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userAuthService.registerUser(userRegisterRequestData));
    }
}
