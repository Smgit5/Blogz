package com.suman.blogz.services.impl;

import com.suman.blogz.entities.Comments;
import com.suman.blogz.entities.MyUser;
import com.suman.blogz.entities.Posts;
import com.suman.blogz.exceptions.ResourceNotFoundException;
import com.suman.blogz.payloads.request.CommentRequest;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.response.CommentResponse;
import com.suman.blogz.repository.CommentRepository;
import com.suman.blogz.repository.MyUserRepository;
import com.suman.blogz.services.CommentService;
import com.suman.blogz.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MyUserRepository myUserRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostService postService, MyUserRepository myUserRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.myUserRepository = myUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentResponse createComment(CommentRequest newComment, Integer postId, Integer userId) {
        Posts post = modelMapper.map(postService.getPostById(postId), Posts.class);
        MyUser user = myUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Comments comment = Comments.builder()
                .commentBody(newComment.getCommentBody())
                .commentDate(new Date())
                .post(post)
                .user(user)
                .build();
        commentRepository.save(comment);

//        MyUserResponse userOTheComment = modelMapper.map(user, MyUserResponse.class);
        return modelMapper.map(comment, CommentResponse.class);
    }

    @Override
    public CommentResponse updateComment(Integer commentId, CommentRequest updatedComment) {
        Comments comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", commentId));
        comment.setCommentBody(updatedComment.getCommentBody());
        comment.setCommentDate(new Date());
        commentRepository.save(comment);

//        MyUserResponse userOTheComment = modelMapper.map(comment.getUser(), MyUserResponse.class);
        return modelMapper.map(comment, CommentResponse.class);
    }

    @Override
    public ApiResponse deleteComment(Integer commentId) {
        if(commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return new ApiResponse("Successfully deleted comment", true);
        }
        return new ApiResponse("Comment does not exist", false);
    }
}
