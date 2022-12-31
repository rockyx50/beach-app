package com.example.demo.Domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    public String sendEmail(List<String> recipientList, String header, String body) throws MessagingException{

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderUsername, senderPassword);
                    }
                });

//        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(String.join(", ", recipientList))
            );
            message.setSubject(header);
            message.setText(body);

            Transport.send(message);

            System.out.println("Done");
            return "Email successfully sent!";

//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return "Error! Unable to send email.";
//        }
    }
    @Value("${email.sender.username}")
    private String senderUsername;

    @Value("${email.sender.password}")
    private String senderPassword;

}

