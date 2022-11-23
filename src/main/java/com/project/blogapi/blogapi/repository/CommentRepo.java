package com.project.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapi.blogapi.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

    
}
