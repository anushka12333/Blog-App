package com.project.blogapi.blogapi.services.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.blogapi.blogapi.entities.Category;
import com.project.blogapi.blogapi.entities.Post;
import com.project.blogapi.blogapi.entities.User;
import com.project.blogapi.blogapi.exceptions.ResourceNotFoundException;
import com.project.blogapi.blogapi.payloads.PostDto;
import com.project.blogapi.blogapi.payloads.PostResponse;
import com.project.blogapi.blogapi.repository.CategoryRepo;
import com.project.blogapi.blogapi.repository.PostRepo;
import com.project.blogapi.blogapi.repository.UserRepo;
import com.project.blogapi.blogapi.services.PostService;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
       Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ategory id", categoryId));
        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddeddate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);      
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
       Sort sort = (sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
       
        Pageable p =PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost=this.postRepo.findAll(p);
       List<Post> allposts = pagePost.getContent();
       List<PostDto> postDtos=allposts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
       PostResponse postResponse=new PostResponse();
       postResponse.setContent(postDtos);
       postResponse.setPageNumber(pagePost.getNumber());
       postResponse.setPageSize(pagePost.getSize());
       postResponse.setLastPages(pagePost.isLast());
       postResponse.setTotalElements(pagePost.getTotalElements());
       postResponse.setTotalPages(pagePost.getTotalPages());
       return postResponse;

    }

   

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
      Post post=  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatepost = this.postRepo.save(post);
        return this.modelMapper.map(updatepost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id ", postId));
        this.postRepo.delete(post);
       
        
    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id ", postId));
        return this.modelMapper.map(post, PostDto.class);
       
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
        List<Post> posts = this.postRepo.findAllByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
      List<Post> posts= this.postRepo.searchByTitle(keyword);
      List<PostDto> postDtos= posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    
}
