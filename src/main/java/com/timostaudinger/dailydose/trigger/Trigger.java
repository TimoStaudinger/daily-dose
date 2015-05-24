package com.timostaudinger.dailydose.trigger;


import com.timostaudinger.dailydose.DailyDose;
import com.timostaudinger.dailydose.util.Frequency;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class Trigger {

    // Trigger daily at 8 AM
    @Schedule(hour = "8")
    public void daily() {
        DailyDose.execute(Frequency.DAILY);
    }

}
