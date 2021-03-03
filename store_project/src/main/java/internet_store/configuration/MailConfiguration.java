package internet_store.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    @Value("${mail}")
    private String userMail;
    @Value("${password}")
    private String userPassword;
    @Value("${smtpServer}")
    private String smtpServer;
    @Value("${port}")
    private int port;
    @Value("${smtp}")
    private String transportProtocol;
    @Value("${smtp.auth}")
    private boolean smtpAuth;
    @Value("${smtp.starttls}")
    private boolean smtpStartTls;
    @Value("${debug}")
    private boolean debug;

    @Bean
    public JavaMailSender mailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpServer);
        mailSender.setPort(port);

        mailSender.setUsername(userMail);
        mailSender.setPassword(userPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", transportProtocol);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", smtpStartTls);
        props.put("mail.debug", debug);

        return mailSender;
    }
}