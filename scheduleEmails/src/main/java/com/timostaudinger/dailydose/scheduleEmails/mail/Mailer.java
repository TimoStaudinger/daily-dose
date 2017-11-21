package com.timostaudinger.dailydose.scheduleEmails.mail;

import com.timostaudinger.dailydose.common.exception.MailException;
import com.timostaudinger.dailydose.common.model.dto.User;
import com.timostaudinger.dailydose.common.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class Mailer {
    public void sendHtmlMail(String subject, String content, List<User> recipients) throws MailException {
        Session session = getSession();

        recipients.stream().map(User::getEmail).forEach(email -> sendMessage(subject, content, session, email));

    }

    private void sendMessage(String subject, String content, Session session, String email) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(Properties.get("smtp_from")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // TODO: logging
        }
    }

    private Session getSession() {
        final String username = Properties.get("smtp_user");
        final String password = Properties.get("smtp_password");

        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", Properties.get("smtp_host"));
        props.put("mail.smtp.socketFactory.port", Properties.get("smtp_port"));
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", Properties.get("smtp_port"));

        System.out.println("Before Auth");

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        return Session.getInstance(props,
                authenticator);
    }
}
