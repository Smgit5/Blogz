package com.suman.blogz.repository;

import com.suman.blogz.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
}
