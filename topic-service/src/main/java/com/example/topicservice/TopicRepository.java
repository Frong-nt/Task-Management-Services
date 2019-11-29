package com.example.topicservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TopicRepository extends MongoRepository<Topic, String> {
    Topic findByIssueID(String id);
}
