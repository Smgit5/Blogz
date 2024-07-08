package com.suman.blogz.payloads.dto;

import com.suman.blogz.entities.Comments;
import com.suman.blogz.payloads.dto.CategoryDto;
import com.suman.blogz.payloads.dto.MyUserDto;
import com.suman.blogz.payloads.response.CommentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PostDto {
    private Integer postId;
    private String postTitle;
    private String postBody;
    private Date postDate;

    private CategoryDto category;
    private MyUserDto user;
    private Set<CommentResponse> commentsForThisPost = new HashSet<>();
}
