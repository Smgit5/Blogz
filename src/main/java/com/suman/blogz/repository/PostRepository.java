package com.suman.blogz.repository;

import com.suman.blogz.entities.Category;
import com.suman.blogz.entities.Posts;
import com.suman.blogz.entities.MyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Integer> {
    Page<Posts> findAllByCategory(Category category, Pageable pageable);
    Page<Posts> findAllByUser(MyUser user, Pageable pageable);
    Page<Posts> findByPostTitleContainingIgnoreCaseOrPostBodyContainingIgnoreCase(
            String searchTitle, String searchBody, Pageable pageable
    );
}
