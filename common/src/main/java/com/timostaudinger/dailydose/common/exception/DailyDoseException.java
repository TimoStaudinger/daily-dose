package com.timostaudinger.dailydose.common.exception;

abstract public class DailyDoseException extends Exception {
    public DailyDoseException(String message) {
        super(message);
    }

    public DailyDoseException(String message, Exception cause) {
        super(message, cause);
    }

    public DailyDoseException(Exception e) {
        super(e);
    }
}
