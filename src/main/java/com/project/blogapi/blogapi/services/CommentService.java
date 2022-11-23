package com.project.blogapi.blogapi.services;

import com.project.blogapi.blogapi.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
