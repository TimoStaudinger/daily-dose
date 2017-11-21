package com.timostaudinger.dailydose.common.exception;

public class RedditAuthException extends DailyDoseException {
    public RedditAuthException(String message, Exception cause) {
        super(message, cause);
    }
}
