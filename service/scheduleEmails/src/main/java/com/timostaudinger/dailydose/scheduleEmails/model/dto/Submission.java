package com.timostaudinger.dailydose.scheduleEmails.model.dto;

abstract class Submission {
    protected String title;
    protected String url;

    public Submission(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
