package com.timostaudinger.dailydose;

import com.timostaudinger.dailydose.exception.RedditAuthException;
import com.timostaudinger.dailydose.exception.RedditLoadException;
import com.timostaudinger.dailydose.model.dao.RedditDAO;
import com.timostaudinger.dailydose.util.Frequency;
import net.dean.jraw.models.Submission;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DailyDose {

    private Frequency frequency;
    private Submission submission;

    private DailyDose(Frequency frequency) {
        this.frequency = frequency;
    }

    public static void execute(Frequency frequency) {

        DailyDose dailyDose = new DailyDose(frequency);
        dailyDose.startProcess();

    }

    private void startProcess() {
        try {

            loadSubmission();

        } catch (RedditLoadException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        } catch (RedditAuthException e) {
            Logger.getLogger("DailyDose.startProcess()").log(Level.SEVERE, e.getMessage());
        }
    }

    private void loadSubmission() throws RedditLoadException, RedditAuthException {
        submission = RedditDAO.getTopOf("getmotivated", frequency);
    }
}
