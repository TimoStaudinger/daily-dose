package com.timostaudinger.dailydose.scheduleEmails.mail;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.timostaudinger.dailydose.common.model.dto.User;

import java.util.List;
import java.util.stream.Collectors;

public class Mailer {
    private String sender;

    public Mailer (final String sender) {
        this.sender = sender;
    }

    public void sendHtmlMail(String subject, String content, List<User> recipients) {
        List<String> toAddresses = recipients.stream().map(User::getEmail).collect(Collectors.toList());

        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(toAddresses))
                .withMessage(
                        new Message()
                                .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(content)))
                                .withSubject(new Content().withCharset("UTF-8").withData(subject)))
                .withSource(this.sender);

        client.sendEmail(request);
    }
}
