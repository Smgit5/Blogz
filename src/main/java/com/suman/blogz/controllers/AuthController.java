package com.suman.blogz.controllers;

import com.suman.blogz.payloads.request.RefreshTokenRequest;
import com.suman.blogz.payloads.request.UserLoginRequestData;
import com.suman.blogz.payloads.response.JwtResponse;
import com.suman.blogz.services.impl.securityService.UserAuthService;
import com.suman.blogz.services.impl.securityService.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogz/auth")
public class AuthController {
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody UserLoginRequestData userLoginRequestData) {
        return ResponseEntity.status(HttpStatus.OK).body(userAuthService.loginUser(userLoginRequestData));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshTokenOfUser(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(refreshTokenService.refreshTokenOfUser(refreshTokenRequest));
    }
}
