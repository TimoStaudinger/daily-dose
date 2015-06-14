package com.timostaudinger.dailydose.mail;

import com.timostaudinger.dailydose.exception.MailException;
import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class Mailer {
    public void sendHtmlMail(String subject, String content, List<User> recipients) throws MailException {
        Session session = getSession();

        for (User recipient : recipients) {
            try {
                Message message = buildMessage(subject, content, session, recipient);
                Transport.send(message);
            } catch (MessagingException e) {
                throw new MailException(e);
            }
        }
    }

    private Message buildMessage(String subject, String content, Session session, User recipient) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Properties.get("smtp_from")));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient.getEmail()));
        message.setSubject(subject);
        message.setContent(content, "text/html; charset=utf-8");
        return message;
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
