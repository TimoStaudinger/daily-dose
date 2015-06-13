package com.timostaudinger.dailydose;

import com.timostaudinger.dailydose.exception.DailyDoseException;
import com.timostaudinger.dailydose.mail.Mailer;
import com.timostaudinger.dailydose.model.dao.RedditDAO;
import com.timostaudinger.dailydose.model.dao.UserDAO;
import com.timostaudinger.dailydose.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.model.dto.SelfPostSubmission;
import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.render.RedditMailRenderer;
import com.timostaudinger.dailydose.render.SubmissionCleaner;
import com.timostaudinger.dailydose.util.Frequency;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DailyDose {

    private static final String SUBREDDIT = "getmotivated";

    private DailyDose() {
    }

    public static void startProcess(Frequency frequency) {
        try {
            List<User> users = new UserDAO().findAll(frequency);

            ImageSubmission image = SubmissionCleaner.cleanImageSubmission(RedditDAO.getTopImageOf(SUBREDDIT, frequency));
            SelfPostSubmission quote = SubmissionCleaner.cleanSelfPostSubmission(RedditDAO.getTopSelfPostOf(SUBREDDIT, frequency));

            String message = new RedditMailRenderer(quote, image).render();
            String subject = image.getTitle();

            Mailer mailer = new Mailer();

            mailer.sendHtmlMail(subject, message, users);

        } catch (DailyDoseException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        }
    }
}
