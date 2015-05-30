package com.timostaudinger.dailydose;

import com.timostaudinger.dailydose.exception.RedditAuthException;
import com.timostaudinger.dailydose.exception.RedditLoadException;
import com.timostaudinger.dailydose.model.dao.RedditDAO;
import com.timostaudinger.dailydose.model.dao.UserDAO;
import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.util.Frequency;
import net.dean.jraw.models.Submission;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DailyDose {

    private DailyDose() {
    }

    public static void execute(Frequency frequency) {

        DailyDose dailyDose = new DailyDose();
        dailyDose.startProcess(frequency);

    }

    private void startProcess(Frequency frequency) {
        try {

            Submission submission = loadSubmission(frequency);
            List<User> users = new UserDAO().findAll(frequency);

        } catch (RedditLoadException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        } catch (RedditAuthException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        }
    }

    private Submission loadSubmission(Frequency frequency) throws RedditLoadException, RedditAuthException {
        return RedditDAO.getTopOf("getmotivated", frequency);
    }
}
