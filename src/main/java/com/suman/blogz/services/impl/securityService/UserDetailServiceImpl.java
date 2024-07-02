package com.suman.blogz.services.impl.securityService;

import com.suman.blogz.config.securityConfig.CustomUserDetails;
import com.suman.blogz.entities.MyUser;
import com.suman.blogz.exceptions.ResourceNotFoundException;
import com.suman.blogz.repository.MyUserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new CustomUserDetails(myUser);
    }


}
