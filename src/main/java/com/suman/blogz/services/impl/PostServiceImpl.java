package com.suman.blogz.services.impl;

import com.suman.blogz.entities.Category;
import com.suman.blogz.entities.Posts;
import com.suman.blogz.entities.MyUser;
import com.suman.blogz.payloads.dto.CategoryDto;
import com.suman.blogz.payloads.dto.MyUserDto;
import com.suman.blogz.payloads.dto.PostDto;
import com.suman.blogz.payloads.request.PostRequestData;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.response.CommentResponse;
import com.suman.blogz.payloads.response.PostResponse;
import com.suman.blogz.repository.PostRepository;
import com.suman.blogz.exceptions.ResourceNotFoundException;
import com.suman.blogz.services.CategoryService;
import com.suman.blogz.services.PostService;
import com.suman.blogz.services.MyUserService;
import com.suman.blogz.utils.GenericMapper;
import com.suman.blogz.utils.SortUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GenericMapper genericMapper;


    private PostDto convertToPostDto(Posts post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        Set<CommentResponse> commentResponses = genericMapper.mapSet(post.getComments(), CommentResponse.class);
        postDto.setCommentsForThisPost(commentResponses);
        return postDto;
    }

    public PostResponse createPostResponse(Page<Posts> pageOfPosts) {
        PostResponse postResponse = new PostResponse();
        List<Posts> postsFromDb = pageOfPosts.getContent();
        if(postsFromDb.isEmpty())
            throw new ResourceNotFoundException("No posts found.");
        List<PostDto> allPostDtos = postsFromDb.stream().map(this::convertToPostDto).toList();

        postResponse.setContent(allPostDtos);
        postResponse.setPageNumber(pageOfPosts.getNumber());
        postResponse.setPageSize(pageOfPosts.getSize());
        postResponse.setTotalPages(pageOfPosts.getTotalPages());
        postResponse.setTotalElements(pageOfPosts.getTotalElements());
        postResponse.setLastPage(pageOfPosts.isLast());
        return postResponse;
    }



    @Override
    public ApiResponse createPost(PostRequestData newPost, Integer userId, int categoryId) {

        Posts post = modelMapper.map(newPost, Posts.class);

        post.setPostDate(new Date());
        MyUser user = modelMapper.map(myUserService.getUserByIdForAdmin(userId), MyUser.class);
        post.setUser(user);
        Category category = modelMapper.map(categoryService.getCategoryById(categoryId), Category.class);
        post.setCategory(category);
        postRepository.save(post);
        return new ApiResponse("Successfully posted !", true);
    }

    @Override
    public ApiResponse updatePost(Integer postId, Posts updatedPost) {
       Posts postFromDb = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", postId));
       postFromDb.setPostTitle(updatedPost.getPostTitle());
       postFromDb.setPostBody(updatedPost.getPostBody());
       postFromDb.setCategory(updatedPost.getCategory());
       postFromDb.setPostDate(new Date());
       postRepository.save(postFromDb);
       return new ApiResponse("Successfully updated post !", true);
    }



    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = SortUtil.sortItems(sortBy, sortDir);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Posts> pageOfPosts = postRepository.findAll(pageable);
        return createPostResponse(pageOfPosts);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Posts postFromDb = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", postId));
        return modelMapper.map(postFromDb, PostDto.class);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = SortUtil.sortItems(sortBy, sortDir);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        Category category = modelMapper.map(categoryDto, Category.class);
        Page<Posts> pageOfPosts = postRepository.findAllByCategory(category, pageable);
        return createPostResponse(pageOfPosts);
    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = SortUtil.sortItems(sortBy, sortDir);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        MyUserDto myUserDto = myUserService.getUserByIdForAdmin(userId);
        MyUser userFromDb = modelMapper.map(myUserDto, MyUser.class);
        Page<Posts> pageOfPosts = postRepository.findAllByUser(userFromDb, pageable);
        return createPostResponse(pageOfPosts);
    }

    @Override
    public PostResponse searchPost(String searchKey, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        if(searchKey == null || searchKey.isBlank())
            throw new ResourceNotFoundException("Enter something to search...");
        Sort sort = SortUtil.sortItems(sortBy, sortDir);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Posts> pageOfPosts = postRepository.findByPostTitleContainingIgnoreCaseOrPostBodyContainingIgnoreCase(
                searchKey, searchKey, pageable
        );

        return createPostResponse(pageOfPosts);
    }

    @Override
    public ApiResponse deletePostById(Integer postId) {
        Posts postFromDb = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", postId));
        postRepository.delete(postFromDb);
        return new ApiResponse("Successfully deleted post !", true);
    }

    @Override
    public ApiResponse deleteMultiplePosts(List<Integer> selectedPostIds) {
        selectedPostIds.forEach(pId -> postRepository.deleteById(pId));
        return new ApiResponse("Successfully deleted selected posts !", true);
    }


}
