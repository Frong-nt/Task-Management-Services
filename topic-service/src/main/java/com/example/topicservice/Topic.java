package com.example.topicservice;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.*;
@Data
@Document(collection = "topic")
public class Topic {
    @Id
    private String issueID;
    @NotEmpty(message = "IssueName is require")
    private String issueName;
    private String status;   // close  or achieved or pending
    private String description;
    private String by;
    private String dateTime;
    private List<Comments> comments;
    public Topic(){}
    public Topic(String issueID, List<Comments> comments) {
        this.issueID = issueID;
        this.comments = comments;
    }
    public Topic(String issueID, String issueName, String status, String description, String by, String dateTime, List<Comments> comments) {
        this.issueID = issueID;
        this.issueName = issueName;
        this.status = status;
        this.description = description;
        this.by = by;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
    public void setAtri(Topic topic) {
        this.issueID = topic.issueID;
        this.issueName = topic.issueName;
        this.status = topic.status;
        this.description = topic.description;
        this.by = topic.by;
        this.dateTime = topic.dateTime;
        this.comments = topic.comments;
    }
    public void insertComment(Comments comment){
        this.comments.add(comment);
    }
    public void deleteComment(String id){
        for(int i=0;i<comments.size();i++){
            if(comments.get(i).getId().equals(id)){
                comments.remove(i);
            }
        }
    }
    public void updateComment(String id, Comments comments){
        for(int i=0;i<this.comments.size();i++){
            if(this.comments.get(i).getId().equals(id)){
                this.comments.get(i).setBy(comments.getBy());
                this.comments.get(i).setMsg(comments.getMsg());
                this.comments.get(i).setDateTime(comments.getDateTime());
            }
        }
    }

}
