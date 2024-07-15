package com.ajay.blog_app.repositories;

import com.ajay.blog_app.dto.response.PostResponse;
import com.ajay.blog_app.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByAuthorUserId(Long authorId);

    List<Post> findByTagsIn(List<String> tags);

    List<Post> findAllByTopicTopicId(Long topicId);
}
