package com.suman.blogz.payloads.dto;

import com.suman.blogz.payloads.dto.CategoryDto;
import com.suman.blogz.payloads.dto.MyUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private Integer postId;
    private String postTitle;
    private String postBody;

    private CategoryDto category;
    private MyUserDto user;
}
