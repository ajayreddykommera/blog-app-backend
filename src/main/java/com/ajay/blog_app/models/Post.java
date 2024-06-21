package com.ajay.blog_app.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "posts")
@Data
public class Post {

    @Id
    private String postId;

    @NotBlank
    @Indexed(unique = true)
    private String postTitle;

    @NotBlank
    private String body;

    private List<String> tags;

    @Indexed
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @DBRef(lazy = true)
    private List<Comment> comments;

    @DBRef(lazy = true)
    private List<Vote> likes;

    @DBRef
    private Topic topic;

    @DBRef
    private User author;
}
