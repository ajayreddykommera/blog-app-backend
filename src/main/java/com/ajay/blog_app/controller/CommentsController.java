package com.ajay.blog_app.controller;

import com.ajay.blog_app.dto.request.CommentRequest;
import com.ajay.blog_app.dto.response.CommentResponse;
import com.ajay.blog_app.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentsController {

    private final CommentService commentService;

    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestBody CommentRequest commentRequest) {

        boolean isSuccess = commentService.addCommentToPost(commentRequest);
        if (isSuccess) {
            return new ResponseEntity<>("Comment added Successfully", HttpStatus.CREATED);
        } else {
            throw new RuntimeException("Something went wrong while adding comment");
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPostId(@PathVariable("postId") String postId) {
        List<CommentResponse> commentResponseList = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") String commentId) {
        boolean deleted = commentService.deleteComment(commentId);
        if (deleted) {
            return new ResponseEntity<>("commented deleted successfully", HttpStatus.OK);
        } else {
            throw new RuntimeException("unable to delete comment");
        }
    }
}

