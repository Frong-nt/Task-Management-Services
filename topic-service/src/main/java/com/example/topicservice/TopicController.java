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
    @RequestMapping(value = "/{issueID}")
    public Topic get(@PathVariable("issueID") String taskID) {
        return this.repository.findByIssueID(taskID);
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<Topic>> insertTopic(@RequestBody Topic topic) {
        this.repository.insert(topic);
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }
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

    @RequestMapping(value = "/{issueID}", method = RequestMethod.DELETE)
    public  ResponseEntity<List<Topic>> deleteTopic(@PathVariable String issueID) {
        repository.delete(repository.findByIssueID(issueID));
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }
}
