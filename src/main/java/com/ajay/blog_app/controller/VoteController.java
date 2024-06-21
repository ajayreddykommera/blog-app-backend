package com.ajay.blog_app.controller;

import com.ajay.blog_app.dto.request.VoteRequest;
import com.ajay.blog_app.dto.response.VoteResponse;
import com.ajay.blog_app.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/addVote")
    public ResponseEntity<String> vote(@RequestBody VoteRequest voteRequest) {

        boolean isVoted = voteService.vote(voteRequest);
        if (isVoted) {
            return new ResponseEntity<>("Vote added Successfully", HttpStatus.CREATED);
        } else {
            throw new RuntimeException("Something went wrong while voting");
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<VoteResponse>> getAllVotesByPostId(@PathVariable("postId") String postId) {
        List<VoteResponse> voteResponses = voteService.getAllVotesByPostId(postId);
        return new ResponseEntity<>(voteResponses, HttpStatus.OK);
    }

}

