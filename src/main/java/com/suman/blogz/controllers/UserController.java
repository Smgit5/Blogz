package com.suman.blogz.controllers;

import com.suman.blogz.payloads.request.RefreshTokenRequest;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.MyUserDto;
import com.suman.blogz.payloads.UserProfile;
import com.suman.blogz.payloads.response.JwtResponse;
import com.suman.blogz.services.MyUserService;
import com.suman.blogz.services.impl.securityService.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/blogz")
public class UserController {
    @Autowired
    private MyUserService myUserService;

    @PutMapping("/normal-user/update/{userId}")
    public ResponseEntity<ApiResponse> updateUserProfile(@PathVariable Integer userId, @Valid @ModelAttribute UserProfile userProfile) {
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.updateUserProfile(userId, userProfile));
    }

//    @PutMapping("/update-pw/user/{userId}")
//    public ResponseEntity<ApiResponse> updateUserPassword(@PathVariable Integer userId, @RequestBody String newPassword) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserPassword(userId, newPassword));
//    }

    @PutMapping("/admin/update-role/userId/{userId}")
    public ResponseEntity<ApiResponse> updateRoleOfUserForAdmin(@PathVariable int userId, @RequestBody Set<Integer> roleIds) {
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.updateRoleOfUserForAdmin(userId, roleIds));
    }
    @GetMapping("/admin/{userId}")
    public ResponseEntity<MyUserDto> getUserByIdForAdmin(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.getUserByIdForAdmin(userId));
    }
    @GetMapping("/admin/all-users")
    public ResponseEntity<List<MyUserDto>> getAllUsersForAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.getAllUsersForAdmin());
    }
    @DeleteMapping("/normal-user/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.deleteUserById(userId));
    }
    @DeleteMapping("/admin/delete-multiple-users")
    public ResponseEntity<ApiResponse> deleteMultipleUsersForAdmin(@RequestBody List<Integer> usersListToDelete) {
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.deleteMultipleUsersForAdmin(usersListToDelete));
    }
}
