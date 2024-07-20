package com.ajay.blog_app.service.impl;

import com.ajay.blog_app.dto.request.PostRequest;
import com.ajay.blog_app.dto.response.CommentResponse;
import com.ajay.blog_app.dto.response.MessageResponse;
import com.ajay.blog_app.dto.response.PostResponse;
import com.ajay.blog_app.models.Comment;
import com.ajay.blog_app.models.Post;
import com.ajay.blog_app.models.Topic;
import com.ajay.blog_app.models.User;
import com.ajay.blog_app.repositories.CommentRepository;
import com.ajay.blog_app.repositories.PostRepository;
import com.ajay.blog_app.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public String addPost(PostRequest postRequest) {
        Post post = new Post();
        post.setPostTitle(postRequest.getPostTitle());
        post.setPostSummary(postRequest.getPostSummary());
        post.setPostBody(postRequest.getPostBody());
        post.setCreatedAt(LocalDateTime.now());
        post.setTags(postRequest.getTags());
        User author = new User();
        author.setUserId(postRequest.getAuthorID());
        post.setAuthor(author);
        Topic topic = new Topic();
        topic.setTopicId(postRequest.getTopicId());
        post.setTopic(topic);
        Post res = postRepository.save(post);
        return "Post saved with Post id " + res.getPostId();
    }

    @Override
    public PostResponse getPostById(Long postId) {
        return postRepository.findById(postId)
                .map(this::buildPostResponse)
                .orElseThrow(() -> new IllegalArgumentException("Post ID not found"));
    }

    @Override
    public List<PostResponse> getPostsByAuthorId(Long authorId) {
        return postRepository.findAllByAuthorUserId(authorId).stream()
                .map(this::buildPostResponse)
                .toList();
    }

    @Override
    public List<PostResponse> getPostsByTags(List<String> tags) {
        log.info("tags {}", tags);
        return postRepository.findByTagsIn(tags).stream()
                .map(this::buildPostResponse)
                .toList();
    }

    @Override
    public boolean updatePost(Long postId, PostRequest postRequest) {
        return postRepository.findById(postId)
                .map(post -> {
                    post.setPostTitle(postRequest.getPostTitle());
                    post.setPostBody(postRequest.getPostBody());
                    post.setPostSummary(postRequest.getPostSummary());
                    post.setTags(postRequest.getTags());
                    post.setModifiedAt(LocalDateTime.now());
                    postRepository.save(post);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::buildPostResponse)
                .toList();
    }

    @Override
    public MessageResponse deletePost(Long postId) {
        MessageResponse response = new MessageResponse();
        try {
            boolean existsById = postRepository.existsById(postId);
            if (existsById) {
                postRepository.deleteById(postId);
                response.setCode(0);
                response.setMessage("post with post id " + postId + " deleted successfully");
            } else {
                response.setCode(1);
                response.setMessage("Post with post id " + postId + " not found");
            }
        } catch (Exception e) {
            response.setCode(1);
            response.setMessage("Something went wrong while deleting post");
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public List<PostResponse> getPostsByTopic(Long topicId) {
        List<Post> postResponseList = postRepository.findAllByTopicTopicId(topicId);
        return postResponseList.stream()
                .map(this::buildPostResponse)
                .toList();
    }

    private PostResponse buildPostResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setUserID(post.getAuthor().getUserId());
        postResponse.setPostId(post.getPostId());
        postResponse.setTags(post.getTags());
        postResponse.setPostBody(post.getPostBody());
        postResponse.setPostTitle(post.getPostTitle());
        postResponse.setPostSummary(post.getPostSummary());
        postResponse.setCreatedAt(post.getCreatedAt());
        postResponse.setModifiedAt(post.getModifiedAt());
        postResponse.setComments(getCommentResponseList(post.getPostId()));
        return postResponse;
    }

    private CommentResponse buildCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comment.getCommentId());
        commentResponse.setComment(comment.getComment());
        commentResponse.setUserID(comment.getCommentedBy().getUserId());
        commentResponse.setPostID(comment.getPost().getPostId());
        return commentResponse;
    }

    private List<CommentResponse> getCommentResponseList(Long postId) {
        return commentRepository.findByPostPostId(postId).stream()
                .map(this::buildCommentResponse)
                .toList();
    }
}
