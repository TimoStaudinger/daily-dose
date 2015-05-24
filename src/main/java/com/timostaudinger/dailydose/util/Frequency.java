package com.timostaudinger.dailydose.util;

public enum Frequency {
    DAILY(0),
    WEEKLY(1),
    MONTHLY(2);

    private final int id;

    Frequency(int id) {
        this.id = id;
    }

    int getId() {
        return this.id;
    }
}
