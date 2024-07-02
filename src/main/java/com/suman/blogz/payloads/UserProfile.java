package com.suman.blogz.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "Must have minimum 3 characters ! (only alphabets)")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Must have minimum 2 characters ! (only alphabets)")
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Please enter a correct email id")
    private String email;

    private String about;
}
