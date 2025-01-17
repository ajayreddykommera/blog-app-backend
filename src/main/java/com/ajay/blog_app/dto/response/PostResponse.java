package com.ajay.blog_app.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {

    private String postId;
    private String postTitle;
    private String body;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponse> comments;
    private List<VoteResponse> likes;
    private String userID;
}
