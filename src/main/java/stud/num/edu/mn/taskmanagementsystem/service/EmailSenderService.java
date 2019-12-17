package stud.num.edu.mn.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Service("emailSenderService")
public class EmailSenderService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void sendMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("daornit2@gmail.com");
        mailMessage.setSubject("Бүртгэлээ баталгаажуулна уу!");
        mailMessage.setFrom("orjuun2012@gmail.com");
        mailMessage.setText("Энэхүү URL ээр орж өөрийн бүртгэлээ баталгаажуулна уу : "
                + "http://localhost:4000/confirm-account?token=");
        javaMailSender.send(mailMessage);
    }

    public void sendmailD() throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("orjuun2012@gmail.com", "95855130");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("orjuun2012@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("orjuun2012@gmail.com"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
