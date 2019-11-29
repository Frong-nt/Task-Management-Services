package com.example.topicservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicRepository repository;
    @RequestMapping("/")
    public List<Topic> home(){
        return repository.findAll();
    }

    //get from issueid
    @RequestMapping(value = "/{issueID}")
    public Topic get(@PathVariable("issueID") String issueID) {
        return this.repository.findByIssueID(issueID);
    }

    // insert issue
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<Topic>> insertTopic(@RequestBody Topic topic) {
        this.repository.insert(topic);
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }

    //update issue
    @RequestMapping(value = "/{issueID}", method = RequestMethod.PUT)
    public ResponseEntity<List<Topic>> modifyByIssueID(@PathVariable("issueID") String issueID, @Valid @RequestBody Topic topic) {
        List<Topic> topics = this.repository.findAll();
        for(int i=0;i<topics.size();i++){
            if(topics.get(i).getIssueID().equals(issueID)){
                topics.get(i).setAtri(topic);
                this.repository.saveAll(topics);
                break;
            }
        }
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);

    }

    // delete issue
    @RequestMapping(value = "/{issueID}", method = RequestMethod.DELETE)
    public  ResponseEntity<List<Topic>> deleteTopic(@PathVariable String issueID) {
        repository.delete(repository.findByIssueID(issueID));
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }


    //comment crud

    //get specific comment from issue
    @RequestMapping(value = "/{issueID}/{commentID}")
    public Comments getComment(@PathVariable("issueID") String issueID,@PathVariable("commentID") String commentID) {
        for(Comments comment : this.repository.findByIssueID(issueID).getComments()){
            if(comment.getId().equals(commentID)){
                return comment;
            }
        }
        return null;
    }

    // insert comment from specific issue
    @RequestMapping(value = "/{issueID}", method = RequestMethod.POST)
    public ResponseEntity<List<Topic>> insertComment(@RequestBody Comments comment, @PathVariable String issueID) {
        comment.setId(""+(this.repository.findByIssueID(issueID).getComments().size()+1));

        List<Topic> topics = this.repository.findAll();
        for(int i=0;i<topics.size();i++){
            if(topics.get(i).getIssueID().equals(issueID)){
                topics.get(i).insertComment(comment);
                this.repository.saveAll(topics);
                break;
            }
        }
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }

    //delete comment from specific issue
    @RequestMapping(value = "/{issueID}/{commentID}", method = RequestMethod.DELETE)
    public  ResponseEntity<List<Topic>> deleteComment(@PathVariable String issueID, @PathVariable String commentID) {
        List<Topic> topics = this.repository.findAll();
        for(int i=0;i<topics.size();i++){
            if(topics.get(i).getIssueID().equals(issueID)){
                topics.get(i).deleteComment(commentID);
                this.repository.saveAll(topics);
                break;
            }
        }
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }

    //update specific comment from specific issue
    @RequestMapping(value = "/{issueID}/{commentID}", method = RequestMethod.PUT)
    public ResponseEntity<List<Topic>> updateComment(@PathVariable("issueID") String issueID, @PathVariable String commentID,@RequestBody Comments comment) {
        List<Topic> topics = this.repository.findAll();
        for(int i=0;i<topics.size();i++){
            if(topics.get(i).getIssueID().equals(issueID)){
                topics.get(i).updateComment(commentID,comment);
                this.repository.saveAll(topics);
                break;
            }
        }
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);

    }
}
