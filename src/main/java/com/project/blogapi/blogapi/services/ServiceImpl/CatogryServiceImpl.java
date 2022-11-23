package com.project.blogapi.blogapi.services.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogapi.blogapi.entities.Category;
import com.project.blogapi.blogapi.exceptions.ResourceNotFoundException;
import com.project.blogapi.blogapi.payloads.CategoryDto;
import com.project.blogapi.blogapi.repository.CategoryRepo;
import com.project.blogapi.blogapi.services.CategoryService;
@Service
public class CatogryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
     
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category cat =this.modelMapper.map(categoryDto,Category.class);
      Category addedCat =this.categoryRepo.save(cat);
       return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
       Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
       cat.setCategoryTitle(categoryDto.getCategoryTitle());
       cat.setCategoryDescription(categoryDto.getCategoryDescription());
       Category updateCat = this.categoryRepo.save(cat);

        return this.modelMapper.map(updateCat,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
    List<Category> categories= this.categoryRepo.findAll();
    List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return catDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
        this.categoryRepo.delete(cat);
        
    }
    
}
