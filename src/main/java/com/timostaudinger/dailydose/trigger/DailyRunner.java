package com.timostaudinger.dailydose.trigger;

import com.timostaudinger.dailydose.DailyDose;
import com.timostaudinger.dailydose.util.Frequency;

public class DailyRunner implements Runnable {
    @Override
    public void run() {
        DailyDose.scheduleEmails();
    }
}
