package com.ajay.blog_app.repositories;

import com.ajay.blog_app.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByPostPostIdAndVotedByUserId(Long postId, Long votedById);

    List<Vote> findAllByPostPostId(Long postId);
}
