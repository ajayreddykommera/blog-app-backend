package com.ajay.blog_app.service.impl;

import com.ajay.blog_app.dto.request.VoteRequest;
import com.ajay.blog_app.dto.response.VoteResponse;
import com.ajay.blog_app.models.Post;
import com.ajay.blog_app.models.User;
import com.ajay.blog_app.models.Vote;
import com.ajay.blog_app.repositories.VoteRepository;
import com.ajay.blog_app.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    @Override
    public boolean vote(VoteRequest voteRequest) {
        Optional<Vote> existingVote = voteRepository.findByPostPostIdAndVotedByUserId(voteRequest.getPostId(), voteRequest.getVotedBy());

        if (existingVote.isPresent()) {
            log.info("vote already existed for user");
            Vote vote = existingVote.get();
            vote.setLike(voteRequest.isLike());
            voteRepository.save(vote);
        } else {
            Vote vote = new Vote();
            vote.setLike(voteRequest.isLike());
            Post post = new Post();
            post.setPostId(voteRequest.getPostId());
            vote.setPost(post);
            User user = new User();
            user.setUserId(voteRequest.getVotedBy());
            vote.setVotedBy(user);
            voteRepository.save(vote);
        }
        return true;
    }


    @Override
    public List<VoteResponse> getAllVotesByPostId(String postId) {
        List<Vote> voteList = voteRepository.findAllByPostPostId(postId);
        List<VoteResponse> voteResponseList = new LinkedList<>();
        for (Vote vote : voteList) {
            VoteResponse voteResponse = new VoteResponse();
            voteResponse.setVoteId(vote.getVoteId());
            voteResponse.setLike(vote.isLike());
            voteResponse.setPostId(vote.getPost().getPostId());
            voteResponse.setUserId(vote.getVotedBy().getUserId());
            voteResponseList.add(voteResponse);
        }
        return voteResponseList;

    }
}
