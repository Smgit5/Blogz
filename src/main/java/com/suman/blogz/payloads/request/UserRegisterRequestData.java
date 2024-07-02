package com.suman.blogz.payloads.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestData {
    @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "Must have minimum 3 characters ! (only alphabets)")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Must have minimum 2 characters ! (only alphabets)")
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Please enter a correct email id")
    private String email;

    @Pattern(regexp = "^(?!.*\\s)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_\\W]).{6,10}$", message = "Password must have minimum 6 and maximum 10 characters, at least one lowercase letter, one uppercase letter, one number, one symbol")
    private String password;

    private String about;
}
