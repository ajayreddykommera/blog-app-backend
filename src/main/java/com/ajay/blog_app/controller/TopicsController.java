package com.ajay.blog_app.controller;

import com.ajay.blog_app.dto.request.TopicRequest;
import com.ajay.blog_app.models.Topic;
import com.ajay.blog_app.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@Slf4j
public class TopicsController {

    private final TopicService topicService;

    @PostMapping("/addTopic")
    public ResponseEntity<String> addTopic(@RequestBody TopicRequest topicRequest) {

        boolean isSuccess = topicService.addTopic(topicRequest);
        if (isSuccess) {
            return new ResponseEntity<>("Topic added Successfully", HttpStatus.CREATED);
        } else {
            throw new RuntimeException("Something went wrong while adding topic");
        }
    }
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics(){
       List<Topic> topics =topicService.getAllTopics();
        return new ResponseEntity<>(topics,HttpStatus.OK);
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<String> deleteTopic(@RequestParam("topicId") String topicId) {
        boolean isDeleted = topicService.deleteTopic(topicId);
        if(isDeleted) {
            return new ResponseEntity<>("topic deleted successfully", HttpStatus.OK);
        }else {
            throw new RuntimeException("Something went wrong while adding topic");
        }
    }
}

