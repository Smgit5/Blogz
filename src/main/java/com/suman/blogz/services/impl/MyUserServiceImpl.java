package com.suman.blogz.services.impl;

import com.suman.blogz.repository.RoleRepository;
import com.suman.blogz.entities.Role;
import com.suman.blogz.entities.MyUser;
import com.suman.blogz.repository.MyUserRepository;
import com.suman.blogz.exceptions.ResourceNotFoundException;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.MyUserDto;
import com.suman.blogz.payloads.UserProfile;
import com.suman.blogz.services.MyUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse updateUserProfile(Integer userId, UserProfile userProfile) {
        MyUser user = myUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        user.setFirstName(userProfile.getFirstName());
        user.setLastName(userProfile.getLastName());
        user.setEmail(userProfile.getEmail());
        user.setAbout(userProfile.getAbout());
        myUserRepository.save(user);
        return new ApiResponse("Successfully updated user profile!", true);
    }

//    @Override
//    public ApiResponse updateUserPassword(Integer userId, String newPassword) {
//        User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
//        if(newPassword.equals(user.getPassword()))
//            return new ApiResponse("New password cannot be same as old password", false);
//
//        user.setPassword(newPassword);
//        userDao.save(user);
//        return new ApiResponse("Successfully updated password!", true);
//    }

    @Override
    public ApiResponse updateRoleOfUserForAdmin(int userId, Set<Integer> roleIds) {
        MyUser user = myUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Set<Role> roles = new HashSet<>();
        roleIds.forEach(
                roleId -> roles.add(roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", roleId)))
        );
        roles.forEach(role -> System.out.println(role.getRoleName()));
        user.setRoles(roles);
        myUserRepository.save(user);
        return new ApiResponse("Successfully updated role", true);
    }

    @Override
    public MyUserDto getUserByIdForAdmin(Integer userId) {
        MyUser userFromDb = myUserRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
        return modelMapper.map(userFromDb, MyUserDto.class);
    }

    @Override
    public List<MyUserDto> getAllUsersForAdmin() {
        List<MyUser> userListFromDb = myUserRepository.findAll();
        List<MyUserDto> myUserDtoList = userListFromDb.stream().map(user -> modelMapper.map(user, MyUserDto.class)).toList();
        return myUserDtoList;
    }

    @Override
    public ApiResponse deleteUserById(Integer userId) {
        if(myUserRepository.existsById(userId)) {
            myUserRepository.deleteById(userId);
            return new ApiResponse("Successfully deleted user", true);
        }
        else {
            throw new ResourceNotFoundException("User", userId);
        }
    }

    @Override
    public ApiResponse deleteMultipleUsersForAdmin(List<Integer> usersListToDelete) {
        usersListToDelete.forEach(id -> myUserRepository.deleteById(id));
        return new ApiResponse("Successfully deleted selected users", true);
    }

//    @Override
//    public Optional<MyUser> findByUsername(String username) {
//        return userDao.findByEmail(username);
//    }
}
