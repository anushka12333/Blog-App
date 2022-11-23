package com.project.blogapi.blogapi.services;

import java.util.List;

import com.project.blogapi.blogapi.payloads.CategoryDto;

public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto category, Integer categoryId);

    //get
    CategoryDto getCategoryById(Integer categoryId);

    //getAll
    List<CategoryDto> getAllCategories();

    //delete
    void deleteCategory(Integer categoryId);
}
