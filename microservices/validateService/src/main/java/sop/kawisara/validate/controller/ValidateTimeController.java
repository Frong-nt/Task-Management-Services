package sop.kawisara.validate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sop.kawisara.validate.model.Time;
import sop.kawisara.validate.service.ValidateTimeService;

@RestController
public class ValidateTimeController {
    ValidateTimeService validateTimeService;

    @Autowired
    public ValidateTimeController(ValidateTimeService validateTimeService){
        this.validateTimeService = validateTimeService;
    }

    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello!";
    }

    @PostMapping(value = "/validateTime")
    public String validateTime(@RequestBody Time time){
        return validateTimeService.validate(time);
    }
}