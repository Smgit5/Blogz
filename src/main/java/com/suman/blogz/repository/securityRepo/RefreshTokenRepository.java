package com.suman.blogz.repository.securityRepo;

import com.suman.blogz.entities.MyUser;
import com.suman.blogz.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByMyUser(MyUser myUser);
}
