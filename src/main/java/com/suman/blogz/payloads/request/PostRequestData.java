package com.suman.blogz.payloads.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestData {
    @NotBlank
    private String postTitle;
    @NotBlank
    private String postBody;
}
