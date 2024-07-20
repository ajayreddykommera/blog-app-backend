package com.ajay.blog_app.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {

    private Long postId;
    private String postTitle;
    private String postBody;
    private String postSummary;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponse> comments;
    private List<VoteResponse> likes;
    private Long userID;
}
