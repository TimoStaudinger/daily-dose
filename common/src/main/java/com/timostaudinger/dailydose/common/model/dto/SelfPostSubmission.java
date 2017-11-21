package com.timostaudinger.dailydose.common.model.dto;

public class SelfPostSubmission extends Submission {
    private String content;

    public SelfPostSubmission(String title, String url, String content) {
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
