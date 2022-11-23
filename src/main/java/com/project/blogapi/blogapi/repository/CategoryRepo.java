package com.project.blogapi.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapi.blogapi.entities.Category;

public interface CategoryRepo  extends JpaRepository<Category,Integer>{
    
}
