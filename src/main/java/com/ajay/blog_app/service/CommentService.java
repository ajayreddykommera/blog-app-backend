package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.request.CommentRequest;
import com.ajay.blog_app.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    boolean addCommentToPost(CommentRequest commentRequest);

    boolean deleteComment(Long commentId);

    List<CommentResponse> getCommentsByPostId(Long postId);
}
