package com.timostaudinger.dailydose.exception;

abstract class DailyDoseException extends Exception {
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
