package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.dto.response.PostResponse;
import com.ajay.blog_app.dto.request.PostRequest;

import java.util.List;

public interface PostService {
    String addPost(PostRequest postRequest);

    PostResponse getPostById(Long postId);

    List<PostResponse> getPostsByAuthorId(Long authorId);

    List<PostResponse> getPostsByTags(List<String> tags);


    boolean updatePost(Long postId, PostRequest postRequest);

    List<PostResponse> getAllPosts();

    MessageResponse deletePost(Long postId);

    List<PostResponse> getPostsByTopic(Long topicId);
}
