package email.main.controller;

import email.main.model.Email;
import email.main.service.MailClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "email")
public class EmailController {
    private MailClient mailClient;

    @PostMapping(value = "/send")
    public ResponseEntity<Email> send(@RequestBody Email email){
        mailClient.prepareAndSend(email.getRecipientName(), email.getMessage(email));
        return new ResponseEntity<Email>(email, HttpStatus.OK);
    }
}
