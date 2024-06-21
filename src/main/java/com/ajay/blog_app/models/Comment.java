package com.ajay.blog_app.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String commentId;

    private LocalDateTime commentedDateTime;

    @NotBlank
    private String comment;

    @DBRef
    private User commentedBy;

    @DBRef
    private Post post;
}
