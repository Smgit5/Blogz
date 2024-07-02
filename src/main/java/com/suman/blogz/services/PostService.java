package com.suman.blogz.services;

import com.suman.blogz.entities.Posts;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.PostDto;
import com.suman.blogz.payloads.request.PostRequestData;
import com.suman.blogz.payloads.response.PostResponse;

import java.util.List;

public interface PostService {
    // Create a post
    ApiResponse createPost(PostRequestData newPost, Integer userId, int categoryId);

    // Update a post by id
    ApiResponse updatePost(Integer postId, Posts updatedPost);



    // Get all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // Get post by id
    PostDto getPostById(Integer postId);

    // Get posts by category
    PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // Get posts by User
    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // Search posts
    PostResponse searchPost(String searchKey, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // Delete post by id
    ApiResponse deletePostById(Integer postId);

    // Delete multiple posts at once
    ApiResponse deleteMultiplePosts(List<Integer> selectedPostIds);

}
