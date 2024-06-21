package com.ajay.blog_app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "votes")
public class Vote {

    @Id
    private String voteId;
    private boolean isLike;
    @DBRef
    private User votedBy;
    @DBRef
    private Post post;
}
