package com.ajay.blog_app.dto.response;

import lombok.Data;

@Data
public class CommentResponse {
    private String commentId;
    private String comment;
    private String userID;
    private String postID;
}
