package com.suman.blogz.controllers;

import com.suman.blogz.payloads.request.CommentRequest;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.response.CommentResponse;
import com.suman.blogz.repository.CommentRepository;
import com.suman.blogz.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogz/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add-new-comment/post/{postId}/user/{userId}")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest newComment, @PathVariable Integer postId, @PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(newComment, postId, userId));
    }

    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Integer commentId, @RequestBody CommentRequest updatedComment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.updateComment(commentId, updatedComment));
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(commentId));
    }
}
