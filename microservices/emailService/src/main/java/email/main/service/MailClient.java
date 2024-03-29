package email.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;


@Service
public class MailClient {
    private JavaMailSender mailSender;
    private EmailContentBuilder emailContentBuilder;

    @Autowired
    public MailClient(JavaMailSender mailSender, EmailContentBuilder emailContentBuilder){
        this.mailSender = mailSender;
        this.emailContentBuilder = emailContentBuilder;
    }

    public String prepareAndSend(String[] recipient, String message){
        String text;
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage
                    ,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            messageHelper.setFrom("no-reply@task.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Notification alert");
            String content = emailContentBuilder.build(message);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
            text = "Success";
        }
        catch (MailException e){
            text = e.toString();
        }
        return text;
    }
}
