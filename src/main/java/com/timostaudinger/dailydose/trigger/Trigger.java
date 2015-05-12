package com.timostaudinger.dailydose.trigger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class Trigger {

    @Schedule(second = "0")
    public void daily() {
        Logger logger = LoggerFactory.getLogger(Trigger.class);
        logger.warn("Triggered trigger");
    }

}
