package com.timostaudinger.dailydose.scheduleEmails;

import com.timostaudinger.dailydose.scheduleEmails.businesslayer.SubscriberTools;
import com.timostaudinger.dailydose.common.model.dto.User;
import com.timostaudinger.dailydose.scheduleEmails.mail.Mailer;
import com.timostaudinger.dailydose.scheduleEmails.model.dao.RedditDAO;
import com.timostaudinger.dailydose.scheduleEmails.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.scheduleEmails.model.dto.SelfpostSubmission;
import com.timostaudinger.dailydose.scheduleEmails.render.RedditMailRenderer;
import com.timostaudinger.dailydose.scheduleEmails.render.SubmissionMapper;
import com.timostaudinger.dailydose.scheduleEmails.util.Frequency;

import java.util.List;

public class EmailScheduler {
    private  static final Frequency FREQUENCY = Frequency.DAILY;
    private static final String SUBREDDIT = "getmotivated";
    private static final String MAIL_SENDER = "dailydose@timostaudinger.com";
    private static final String MAIL_SUBJECT = "DailyDose";

    private EmailScheduler() {}

    public static void scheduleEmails() {
        List<User> users = SubscriberTools.findAllActive();

        ImageSubmission imageSubmission = SubmissionMapper.mapImageSubmission(RedditDAO.getTopImageOf(SUBREDDIT, FREQUENCY));
        SelfpostSubmission selfpostSubmission = SubmissionMapper.mapSelfpostSubmission(RedditDAO.getTopSelfPostOf(SUBREDDIT, FREQUENCY));

        String mailBody = new RedditMailRenderer(selfpostSubmission, imageSubmission).render();

        Mailer mailer = new Mailer(MAIL_SENDER);
        mailer.sendHtmlMail(MAIL_SUBJECT, mailBody, users);
    }
}
