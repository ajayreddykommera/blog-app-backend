package com.ajay.blog_app.service.impl;

import com.ajay.blog_app.dto.request.CommentRequest;
import com.ajay.blog_app.dto.response.CommentResponse;
import com.ajay.blog_app.models.Comment;
import com.ajay.blog_app.models.Post;
import com.ajay.blog_app.models.User;
import com.ajay.blog_app.repositories.CommentRepository;
import com.ajay.blog_app.repositories.PostRepository;
import com.ajay.blog_app.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public boolean addCommentToPost(CommentRequest commentRequest) {
        Optional<Post> postResponse = postRepository.findById(commentRequest.getPostID());
        if (postResponse.isPresent()) {
            User user = new User();
            user.setUserId(commentRequest.getUserID());
            Post post = postResponse.get();
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setCommentedBy(user);
            comment.setCommentedDateTime(LocalDateTime.now());
            comment.setComment(commentRequest.getComment());
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CommentResponse> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostPostId(postId);
        List<CommentResponse> commentResponseList = new LinkedList<>();
        for (Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setCommentId(comment.getCommentId());
            commentResponse.setComment(comment.getComment());
            commentResponse.setPostID(comment.getPost().getPostId());
            commentResponse.setUserID(comment.getCommentedBy().getUserId());
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }
}
