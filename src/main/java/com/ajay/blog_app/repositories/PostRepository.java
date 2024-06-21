package com.ajay.blog_app.repositories;

import com.ajay.blog_app.dto.response.PostResponse;
import com.ajay.blog_app.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
    List<Post> findAllByAuthorUserId(String authorId);

    List<Post> findByTagsIn(List<String> tags);

    List<Post> findAllByTopicTopicId(String topicId);
}
