package com.timostaudinger.dailydose.trigger;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class Trigger implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        long initalDelay = getSecondsUntil9Am();

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new DailyRunner(), initalDelay,
                24 * 60 * 60, TimeUnit.SECONDS);
    }

    private long getSecondsUntil9Am() {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("America/New_York");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedExecutionTime;
        zonedExecutionTime = zonedNow.withHour(8).withMinute(0).withSecond(0);
        if (zonedNow.compareTo(zonedExecutionTime) > 0)
            zonedExecutionTime = zonedExecutionTime.plusDays(1);

        Duration duration = Duration.between(zonedNow, zonedExecutionTime);
        return duration.getSeconds();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

}
