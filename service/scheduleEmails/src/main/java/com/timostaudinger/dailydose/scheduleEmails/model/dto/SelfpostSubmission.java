package com.timostaudinger.dailydose.scheduleEmails.model.dto;

public class SelfpostSubmission extends Submission {
    private String content;

    public SelfpostSubmission(String title, String url, String content) {
        super(title, url);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
