package com.timostaudinger.dailydose;

import com.timostaudinger.dailydose.businesslayer.SubscriberTools;
import com.timostaudinger.dailydose.exception.DailyDoseException;
import com.timostaudinger.dailydose.mail.Mailer;
import com.timostaudinger.dailydose.model.dao.RedditDAO;
import com.timostaudinger.dailydose.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.model.dto.SelfPostSubmission;
import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.render.RedditMailRenderer;
import com.timostaudinger.dailydose.render.SubmissionMapper;
import com.timostaudinger.dailydose.util.Frequency;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DailyDose {

    private static final String SUBREDDIT = "getmotivated";

    private DailyDose() {
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
//            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
//        }
    }
}
