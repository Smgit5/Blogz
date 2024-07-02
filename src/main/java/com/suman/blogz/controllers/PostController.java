package com.suman.blogz.controllers;

import com.suman.blogz.entities.Posts;
import com.suman.blogz.utils.AppConstants;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.PostDto;
import com.suman.blogz.payloads.request.PostRequestData;
import com.suman.blogz.payloads.response.PostResponse;
import com.suman.blogz.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogz/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Create a post
    @PostMapping("/normal-user/new-post/user/{userId}/category/{categoryId}")
    public ResponseEntity<ApiResponse> createPost(@Valid @ModelAttribute PostRequestData newPost,
                                                  @PathVariable(value = "userId") Integer userId,
                                                  @PathVariable(value = "categoryId") int categoryId)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(newPost, userId, categoryId));
    }

    // update a post by id
    @PutMapping("/normal-user/update/id/{postId}")
    public  ResponseEntity<ApiResponse> updatePost(@PathVariable Integer postId, @Valid @RequestBody Posts updatedPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.updatePost(postId, updatedPost));
    }



    // Get all posts
    @GetMapping("/show-posts/all")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir));
    }

    // Get post by id
    @GetMapping("/show-posts/id/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(postId));
    }

    // Get posts by category
    @GetMapping("/show-posts/c-id/{categoryId}")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                           @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                           @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                           @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByCategory(categoryId, pageNumber, pageSize, sortBy, sortDir));
    }

    // Get posts by user
    @GetMapping("/show-posts/userId/{userId}")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                       @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                       @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                       @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByUser(userId, pageNumber, pageSize, sortBy, sortDir));
    }

    // Search post
    @GetMapping("/search")
    public ResponseEntity<PostResponse> searchPost(@RequestParam(value = "searchKey", required = false) String searchKey,
                                                   @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        return ResponseEntity.status(HttpStatus.OK).body(postService.searchPost(searchKey, pageNumber, pageSize, sortBy, sortDir));
    }

    // Delete post by id
    @DeleteMapping("/normal-user/delete/id/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePostById(postId));
    }

    // Delete multiple posts at once
    @DeleteMapping("/normal-user/delete-selected-posts")
    public ResponseEntity<ApiResponse> deleteMultiplePosts(@RequestBody List<Integer> selectedPostIds) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deleteMultiplePosts(selectedPostIds));
    }
}
