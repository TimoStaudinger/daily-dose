package com.timostaudinger.dailydose.model.dto;

public class ImageSubmission extends Submission {
    private String imageUrl;

    public ImageSubmission(String title, String url, String imageUrl) {
        super(title, url);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
