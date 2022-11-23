package com.project.blogapi.blogapi.services;

import java.util.List;


import com.project.blogapi.blogapi.payloads.PostDto;
import com.project.blogapi.blogapi.payloads.PostResponse;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get
    PostDto getPostById(Integer postId);
    //getAllPost
    PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    //get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostByUser(Integer userId);

    //get post by search
    List<PostDto> searchPosts(String keyword);
    

}
