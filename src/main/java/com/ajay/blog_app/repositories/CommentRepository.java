package com.ajay.blog_app.repositories;

import com.ajay.blog_app.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByPostPostId(String postId);
}
