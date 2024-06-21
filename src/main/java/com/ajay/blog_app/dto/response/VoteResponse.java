package com.ajay.blog_app.dto.response;

import lombok.Data;

@Data
public class VoteResponse {
    private String voteId;
    private boolean isLike;
    private String userId;
    private String postId;
}
