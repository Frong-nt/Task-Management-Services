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
    @RequestMapping(value = "/{taskID}")
    public Topic get(@PathVariable("taskID") String taskID) {
        return this.repository.findByTaskID(taskID);
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<Topic>> insertTopic(@RequestBody Topic topic) {
        this.repository.insert(topic);
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }
    @RequestMapping(value = "/{taskID}", method = RequestMethod.PUT)
    public ResponseEntity<List<Topic>> modifyPetByTaskID(@PathVariable("taskID") String taskID, @Valid @RequestBody Topic topic) {
        List<Topic> topics = this.repository.findAll();
        for(int i=0;i<topics.size();i++){
            if(topics.get(i).getTaskID().equals(taskID)){
                topics.get(i).setComments(topic.getComments());
                this.repository.saveAll(topics);
                break;
            }
        }
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);

    }

    @RequestMapping(value = "/{taskID}", method = RequestMethod.DELETE)
    public  ResponseEntity<List<Topic>> deleteTopic(@PathVariable String taskID) {
        repository.delete(repository.findByTaskID(taskID));
        return new ResponseEntity<List<Topic>>(this.repository.findAll(), HttpStatus.OK);
    }
}
