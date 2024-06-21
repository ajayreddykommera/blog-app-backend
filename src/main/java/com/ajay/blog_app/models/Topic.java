package com.ajay.blog_app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "topics")
@Data
public class Topic {

    @Id
    private String topicId;
    private String topicName;
    @DBRef
    private List<Post> posts;
}
