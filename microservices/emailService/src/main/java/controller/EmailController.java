package controller;

import Model.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "email")
public class EmailController{

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public ResponseEntity sendEmail (@RequestBody Email mailInfo){
       mailInfo.sendMail(mailInfo.getReceiverEmail(), mailInfo.getReceiverName(), mailInfo.getSenderName());
       return new ResponseEntity(HttpStatus.OK);
    }
}
