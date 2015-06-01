package com.timostaudinger.dailydose.exception;

public class MailException extends Exception {
    public MailException(Exception e) {
        super(e);
    }
}
