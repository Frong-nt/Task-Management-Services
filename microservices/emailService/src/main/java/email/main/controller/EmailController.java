package email.main.controller;

import email.main.model.Email;
import email.main.service.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "email")
public class EmailController {
    private final MailClient mailClient;

    @Autowired
    public EmailController(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    @PostMapping(value = "/send")
    public ResponseEntity<Email> send(@RequestBody Email email){
        mailClient.prepareAndSend(email.getRecipientName(), email.getMessage());
        return new ResponseEntity<Email>(email, HttpStatus.OK);
    }
}
