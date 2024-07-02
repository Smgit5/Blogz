package com.suman.blogz.services.impl.securityService;

import com.suman.blogz.payloads.request.RefreshTokenRequest;
import com.suman.blogz.payloads.response.JwtResponse;
import com.suman.blogz.utils.AppConstants;
import com.suman.blogz.entities.MyUser;
import com.suman.blogz.entities.RefreshToken;
import com.suman.blogz.exceptions.ResourceNotFoundException;
import com.suman.blogz.repository.MyUserRepository;
import com.suman.blogz.repository.securityRepo.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    public RefreshToken generateRefreshToken(String username) {
        MyUser myUser = myUserRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(AppConstants.REFRESH_TOKEN_EXPIRATION))
                .myUser(myUser)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyTokenExpiration(RefreshToken refreshToken) {
        if((refreshToken.getExpiryDate().compareTo(Instant.now())) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired. Please login again.");
        }

        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public JwtResponse refreshTokenOfUser(RefreshTokenRequest refreshTokenRequest){
        return findByToken(refreshTokenRequest.getRefreshToken())
                .map(this::verifyTokenExpiration)
                .map(RefreshToken::getMyUser)
                .map(myUser -> {
                    String accessToken = jwtService.generateToken(myUser.getEmail());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequest.getRefreshToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));

//        RefreshToken refreshToken = findByToken(refreshTokenRequest.getToken());
//        RefreshToken refreshToken1 =
    }
}
