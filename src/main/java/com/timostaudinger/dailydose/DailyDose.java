package com.timostaudinger.dailydose;

import com.timostaudinger.dailydose.exception.MailException;
import com.timostaudinger.dailydose.exception.RedditAuthException;
import com.timostaudinger.dailydose.exception.RedditLoadException;
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

    public static final String SUBREDDIT = "getmotivated";
    public static final String SUBJECT = "DailyDose";

    private DailyDose() {
    }

    public static void execute(Frequency frequency) {

        DailyDose dailyDose = new DailyDose();
        dailyDose.startProcess(frequency);

    }

    private void startProcess(Frequency frequency) {
        try {
            List<User> users = new UserDAO().findAll(frequency);

            ImageSubmission image = SubmissionCleaner.cleanImageSubmission(RedditDAO.getTopImageOf(SUBREDDIT, frequency));
            SelfPostSubmission quote = SubmissionCleaner.cleanSelfPostSubmission(RedditDAO.getTopSelfPostOf(SUBREDDIT, frequency));

            String message = new RedditMailRenderer(quote, image).render();

            Mailer mailer = new Mailer();
            mailer.sendHtmlMail(SUBJECT, message, users);

        } catch (RedditLoadException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        } catch (RedditAuthException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        } catch (MailException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        }
    }
}
