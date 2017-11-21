package com.timostaudinger.dailydose.scheduleEmails;

import com.timostaudinger.dailydose.scheduleEmails.businesslayer.SubscriberTools;
import com.timostaudinger.dailydose.common.model.dto.User;

import java.util.List;

public class EmailScheduler {

    private static final String SUBREDDIT = "getmotivated";

    private EmailScheduler() {
    }

    public static List<User> scheduleEmails() {
//        try {
            List<User> users = SubscriberTools.findAllActive();

//            ImageSubmission image = SubmissionMapper.mapImageSubmission(RedditDAO.getTopImageOf(SUBREDDIT, Frequency.DAILY));
//            SelfPostSubmission quote = SubmissionMapper.mapSelfpostSubmission(RedditDAO.getTopSelfPostOf(SUBREDDIT, Frequency.DAILY));

//            String message = new RedditMailRenderer(quote, image).render();
//            String subject = image.getTitle();
//
//            Mailer mailer = new Mailer();

//            mailer.sendHtmlMail(subject, message, users);

            return users;
//        } catch (DailyDoseException e) {
//            Logger.getLogger("EmailScheduler.startProcess()").log(Level.SEVERE, e.getMessage());
//        }
    }
}
