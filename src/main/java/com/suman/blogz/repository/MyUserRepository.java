package com.suman.blogz.repository;

import com.suman.blogz.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByEmail(String username);
}
