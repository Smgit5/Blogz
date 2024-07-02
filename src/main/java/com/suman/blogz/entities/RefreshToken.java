package com.suman.blogz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int tokenId;

    private String token;

    private Instant expiryDate;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"     // -> primary key field "id" of MyUser class
    )
    private MyUser myUser;
}
