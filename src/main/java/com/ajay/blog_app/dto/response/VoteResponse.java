package com.ajay.blog_app.dto.response;

import lombok.Data;

@Data
public class VoteResponse {
    private Long voteId;
    private boolean isLike;
    private Long userId;
    private Long postId;
}
