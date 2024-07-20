package com.ajay.blog_app.controller;

import com.ajay.blog_app.dto.request.PostRequest;
import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.dto.response.PostResponse;
import com.ajay.blog_app.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@RequestBody PostRequest postRequest) {
        String response = postService.addPost(postRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("postId") Long postId) {
        PostResponse response = postService.getPostById(postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> response = postService.getAllPosts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostResponse>> getPostsByAuthor(@PathVariable("authorId") Long authorId) {
        List<PostResponse> response = postService.getPostsByAuthorId(authorId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<List<PostResponse>> getPostsByTags(@RequestParam("tag") List<String> tags) {
        List<PostResponse> response = postService.getPostsByTags(tags);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<PostResponse>> getPostsByTopics(@PathVariable("topicId") Long topicId) {
        List<PostResponse> response = postService.getPostsByTopic(topicId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        boolean isUpdated = postService.updatePost(postId, postRequest);
        if (isUpdated) {
            return ResponseEntity.ok("Post updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<MessageResponse> deletePostById(@PathVariable("postId") Long postId) {
        MessageResponse response = postService.deletePost(postId);
        try {
            if (response.getCode() == 0) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
