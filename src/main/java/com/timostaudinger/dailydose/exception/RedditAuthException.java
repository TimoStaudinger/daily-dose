package com.timostaudinger.dailydose.exception;

public class RedditAuthException extends RedditException {
    public RedditAuthException(String message, Exception cause) {
        super(message, cause);
    }
}
