package com.email.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "email")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<Email> sendEmail(@RequestBody Email mailInfo) {

        System.out.println("Sending Email...");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailInfo.getReceiverEmail());

        msg.setSubject("Notification from " + mailInfo.getSenderName());
        msg.setText("Hello, " + mailInfo.getReceiverName() + " " + mailInfo.getSenderName() + " have already complete the task! please check out !");

        try {
            javaMailSender.send(msg);
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

        System.out.println("Complete");
        return new ResponseEntity<Email>(mailInfo, HttpStatus.OK);
    }
}
