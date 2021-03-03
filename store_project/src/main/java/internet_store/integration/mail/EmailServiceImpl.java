package internet_store.integration.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender emailSender;
    @Value("${mail}")
    private String sendFromMail;

    public void sendSimpleMessage(String sendTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendFromMail);
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}