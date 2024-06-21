package com.ajay.blog_app.repositories;

import com.ajay.blog_app.models.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {
    Optional<Vote> findByPostPostIdAndVotedByUserId(String postId, String votedById);

    List<Vote> findAllByPostPostId(String postId);
}
