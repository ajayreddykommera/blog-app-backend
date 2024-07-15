package com.ajay.blog_app.service.impl;

import com.ajay.blog_app.dto.request.TopicRequest;
import com.ajay.blog_app.models.Topic;
import com.ajay.blog_app.repositories.TopicRepository;
import com.ajay.blog_app.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    @Override
    public boolean addTopic(TopicRequest topicRequest) {
        Topic topic = new Topic();
        topic.setTopicName(topicRequest.getTopicName());
        Topic saved = topicRepository.save(topic);
        if (saved.getTopicId() != null) {
            return true;
        }
        return true;
    }

    @Override
    public boolean deleteTopic(Long topicId) {
        try {
            topicRepository.deleteById(topicId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
