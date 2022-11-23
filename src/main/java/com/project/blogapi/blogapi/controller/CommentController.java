package com.project.blogapi.blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogapi.blogapi.payloads.ApiResponse;
import com.project.blogapi.blogapi.payloads.CommentDto;
import com.project.blogapi.blogapi.services.CommentService;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    @Autowired
    private CommentService  commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId)
    {
        CommentDto creaComment=this.commentService.createComment(comment, postId);
        return new ResponseEntity<>(creaComment,HttpStatus.CREATED);

    }

    public ResponseEntity <ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment delete Successfully !!",true),HttpStatus.CREATED);
    }
}
