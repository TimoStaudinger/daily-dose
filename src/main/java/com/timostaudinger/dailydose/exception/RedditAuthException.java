package com.timostaudinger.dailydose.exception;

public class RedditAuthException extends DailyDoseException {
    public RedditAuthException(String message, Exception cause) {
        super(message, cause);
    }
}
