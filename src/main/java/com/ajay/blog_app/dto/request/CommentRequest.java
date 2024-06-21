package com.ajay.blog_app.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRequest {
    private String comment;
    private LocalDateTime commentedDateTime;
    private String userID;
    private String postID;
}
