package com.suman.blogz.payloads.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserLoginRequestData {
    private String email;

    private String password;
}
