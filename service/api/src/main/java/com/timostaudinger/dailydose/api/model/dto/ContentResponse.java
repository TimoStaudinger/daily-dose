package com.timostaudinger.dailydose.api.model.dto;

import com.timostaudinger.dailydose.common.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.common.model.dto.SelfpostSubmission;

public class ContentResponse {
    private ImageSubmission image;
    private SelfpostSubmission selfpost;

    public ContentResponse(ImageSubmission image, SelfpostSubmission selfpost) {
        this.image = image;
        this.selfpost = selfpost;
    }

    public ImageSubmission getImage() {
        return image;
    }

    public void setImage(ImageSubmission image) {
        this.image = image;
    }

    public SelfpostSubmission getSelfpost() {
        return selfpost;
    }

    public void setSelfpost(SelfpostSubmission selfpost) {
        this.selfpost = selfpost;
    }
}
