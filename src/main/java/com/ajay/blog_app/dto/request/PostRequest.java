package com.ajay.blog_app.dto.request;

import com.ajay.blog_app.models.Topic;
import lombok.Data;

import java.util.List;

@Data
public class PostRequest {
    private String postTitle;
    private String body;
    private List<String> tags;
    private Long authorID;
    private Long topicId;
}
