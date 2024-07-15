package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.request.VoteRequest;
import com.ajay.blog_app.dto.response.VoteResponse;

import java.util.List;

public interface VoteService {
    boolean vote(VoteRequest voteRequest);

    List<VoteResponse> getAllVotesByPostId(Long postId);
}
