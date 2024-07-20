package com.ajay.blog_app.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PostRequest {
    private String postTitle;
    private String postSummary;
    private String postBody;
    private List<String> tags;
    private Long authorID;
    private Long topicId;
}
