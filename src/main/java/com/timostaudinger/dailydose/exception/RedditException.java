package com.timostaudinger.dailydose.exception;

public class RedditException extends Exception {
    public RedditException(String message) {
        super(message);
    }

    public RedditException(String message, Exception cause) {
        super(message, cause);
    }
}
