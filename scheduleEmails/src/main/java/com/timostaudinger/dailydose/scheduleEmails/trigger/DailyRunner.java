package com.timostaudinger.dailydose.scheduleEmails.trigger;

import com.timostaudinger.dailydose.scheduleEmails.EmailScheduler;

public class DailyRunner implements Runnable {
    @Override
    public void run() {
        EmailScheduler.scheduleEmails();
    }
}
