package email.main;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import email.main.service.MailClient;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmailApplicationTests {

    @Autowired
    private MailClient mailClient;
    private GreenMail smtpServer;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }

    @Test
    public void shouldSendMail() throws Exception {
        //given
        String[] recipient = {"60070002@kmitl.ac.th"};
        String message = "Project successfully";
        //when
        mailClient.prepareAndSend(recipient, message);
    }


    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }
}
