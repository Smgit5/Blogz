package com.suman.blogz.services;

import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.MyUserDto;
import com.suman.blogz.payloads.UserProfile;

import java.util.List;
import java.util.Set;

public interface MyUserService {
    ApiResponse updateUserProfile(Integer userId, UserProfile userProfile);

//    ApiResponse updateUserPassword(Integer userId, String newPassword);

    ApiResponse updateRoleOfUserForAdmin(int userId, Set<Integer> roleIds);
    MyUserDto getUserByIdForAdmin(Integer userId);
    List<MyUserDto> getAllUsersForAdmin();
    ApiResponse deleteUserById(Integer userId);

    ApiResponse deleteMultipleUsersForAdmin(List<Integer> usersListToDelete);


//    Optional<MyUser> findByUsername(String username);
}
