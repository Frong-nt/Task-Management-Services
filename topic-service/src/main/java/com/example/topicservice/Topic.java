package com.example.topicservice;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
@Data
@Document(collection = "topic")
public class Topic {
//    @Id
//    private String _id;
    @Id
    private String taskID;
    private List<Comments> comments;
    public Topic(){}
    public Topic(String taskID, List<Comments> comments) {
        this.taskID = taskID;
        this.comments = comments;
    }

    public String getTaskID() { return taskID; }

    public void setTaskID(String value) { this.taskID = value; }


    public List<Comments> getComments() { return comments; }

    public void setComments(List<Comments> value) { this.comments = value; }

}
