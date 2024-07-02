package com.suman.blogz.services.impl.securityService;

import com.suman.blogz.entities.MyUser;
import com.suman.blogz.entities.RefreshToken;
import com.suman.blogz.entities.Role;
import com.suman.blogz.exceptions.ResourceNotFoundException;
import com.suman.blogz.payloads.request.UserLoginRequestData;
import com.suman.blogz.payloads.request.UserRegisterRequestData;
import com.suman.blogz.payloads.response.JwtResponse;
import com.suman.blogz.repository.MyUserRepository;
import com.suman.blogz.repository.RoleRepository;
import com.suman.blogz.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserAuthService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean userAlreadyExists(String username) {
        Optional<MyUser> myUser = myUserRepository.findByEmail(username);
        return myUser.isPresent();
    }

    private Set<Role> setDefaultUserRole() {
        return Collections.singleton(roleRepository.findByRoleName(AppConstants.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException("Role not found!")));
    }

    public JwtResponse registerUser(UserRegisterRequestData userRegisterRequestData) throws Exception {
        if(userAlreadyExists(userRegisterRequestData.getEmail()))
            throw new Exception("username already exists");

        MyUser myUser = MyUser.builder()
                .firstName(userRegisterRequestData.getFirstName())
                .lastName(userRegisterRequestData.getLastName())
                .email(userRegisterRequestData.getEmail())
                .password(passwordEncoder.encode(userRegisterRequestData.getPassword()))
                .about(userRegisterRequestData.getAbout())
                .roles(setDefaultUserRole())
                .build();

        myUserRepository.save(myUser);

        String accessToken = jwtService.generateToken(myUser.getEmail());
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(myUser.getEmail());

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public JwtResponse loginUser(UserLoginRequestData userLoginRequestData) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestData.getEmail(), userLoginRequestData.getPassword()));
        if(authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(userLoginRequestData.getEmail());
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(userLoginRequestData.getEmail()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        }
        else
            throw new RuntimeException("Wrong credentials !");
    }
}
