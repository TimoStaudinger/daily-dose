package com.timostaudinger.dailydose.util;

import net.dean.jraw.paginators.TimePeriod;

public class FrequencyUtils {
    public static Frequency getNextHigher(Frequency frequency) {
        switch (frequency) {
            case HOURLY:
                return Frequency.DAILY;
            case DAILY:
                return Frequency.WEEKLY;
            case WEEKLY:
                return Frequency.MONTHLY;
            case MONTHLY:
                return Frequency.YEARLY;
            case YEARLY:
                return Frequency.ALL_TIME;
            default:
                return null;
        }
    }

    public static TimePeriod mapFrequencyToTimePeriod(Frequency frequency) {
        switch (frequency) {
            case HOURLY:
                return TimePeriod.HOUR;
            case DAILY:
                return TimePeriod.DAY;
            case WEEKLY:
                return TimePeriod.WEEK;
            case MONTHLY:
                return TimePeriod.MONTH;
            case YEARLY:
                return TimePeriod.YEAR;
            case ALL_TIME:
                return TimePeriod.ALL;
        }

        throw new IllegalArgumentException();
    }
}
