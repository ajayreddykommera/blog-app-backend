package com.ajay.blog_app.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRequest {
    private String comment;
    private LocalDateTime commentedDateTime;
    private Long userID;
    private Long postID;
}
