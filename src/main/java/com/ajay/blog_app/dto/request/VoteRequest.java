package com.ajay.blog_app.dto.request;

import lombok.Data;

@Data
public class VoteRequest {
    private boolean isLike;
    private Long votedBy;
    private Long postId;
}
