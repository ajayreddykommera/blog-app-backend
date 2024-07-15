package com.ajay.blog_app.service;

import com.ajay.blog_app.dto.request.TopicRequest;
import com.ajay.blog_app.models.Topic;

import java.util.List;

public interface TopicService {
    boolean addTopic(TopicRequest topicRequest);

    boolean deleteTopic(Long topicId);

    List<Topic> getAllTopics();
}
