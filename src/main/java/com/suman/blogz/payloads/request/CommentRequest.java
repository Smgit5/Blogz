package com.suman.blogz.payloads.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Integer id;

    @NotBlank
    @Size(max = 1000)
    private String commentBody;
}
