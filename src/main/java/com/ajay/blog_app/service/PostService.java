package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.response.PostResponse;
import com.ajay.blog_app.dto.request.PostRequest;

import java.util.List;

public interface PostService {
    String addPost(PostRequest postRequest);

    PostResponse getPostById(String postId);

    List<PostResponse> getPostsByAuthorId(String authorId);

    List<PostResponse> getPostsByTags(List<String> tags);


    boolean updatePost(String postId, PostRequest postRequest);

    List<PostResponse> getAllPosts();

    boolean deletePost(String postId);

    List<PostResponse> getPostsByTopic(String topicId);
}
