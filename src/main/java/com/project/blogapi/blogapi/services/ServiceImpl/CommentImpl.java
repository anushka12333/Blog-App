package com.project.blogapi.blogapi.services.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogapi.blogapi.entities.Comment;
import com.project.blogapi.blogapi.entities.Post;
import com.project.blogapi.blogapi.exceptions.ResourceNotFoundException;
import com.project.blogapi.blogapi.payloads.CommentDto;
import com.project.blogapi.blogapi.repository.CommentRepo;
import com.project.blogapi.blogapi.repository.PostRepo;
import com.project.blogapi.blogapi.services.CommentService;

@Service
public class CommentImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
      Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
      Comment comment= this.modelMapper.map(commentDto, Comment.class);
      comment.setPost(post);
      Comment savedComment=this.commentRepo.save(comment);
      return this.modelMapper.map(savedComment, CommentDto.class);
        
    }

    @Override
    public void deleteComment(Integer commentId) {
      Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Commnet", "comment id", commentId));
      this.commentRepo.delete(com);
        
    }
    
}
