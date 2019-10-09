package Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Email {
    String receiverEmail;
    String receiverName;
    String senderName;
    String senderEmail;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String receiverEmail, String receiverName, String senderName) {

        System.out.println("Sending Email...");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(receiverEmail);

        msg.setSubject("Notification from" + senderName);
        msg.setText("Hello, " + receiverName+ " " + senderName + "have already complete the task! please check out !");

        try {
            javaMailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Complete");

    }
}
