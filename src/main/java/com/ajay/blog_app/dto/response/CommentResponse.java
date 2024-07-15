package com.ajay.blog_app.dto.response;

import lombok.Data;

@Data
public class CommentResponse {
    private Long commentId;
    private String comment;
    private Long userID;
    private Long postID;
}
