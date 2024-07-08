package com.suman.blogz.services;

import com.suman.blogz.payloads.request.CommentRequest;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.response.CommentResponse;

public interface CommentService {
    CommentResponse createComment(CommentRequest newComment, Integer postId, Integer userId);
    CommentResponse updateComment(Integer commentId, CommentRequest updatedComment);
    ApiResponse deleteComment(Integer commentId);
}
