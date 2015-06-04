package com.timostaudinger.dailydose.render;

import com.timostaudinger.dailydose.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.model.dto.SelfPostSubmission;
import net.dean.jraw.models.Submission;

import java.net.URI;
import java.net.URISyntaxException;

public class SubmissionCleaner {
    public static ImageSubmission cleanImageSubmission(Submission submission) {

        String title = cleanTitle(submission.getTitle());
        String imageUrl = cleanImgurUrl(submission.getUrl());

        ImageSubmission imageSubmission = new ImageSubmission(title, submission.getUrl(), imageUrl);
        return imageSubmission;
    }

    public static SelfPostSubmission cleanSelfPostSubmission(Submission submission) {

        String title = cleanTitle(submission.getTitle());
        String content = submission.getSelftext();

        SelfPostSubmission selfPostSubmission = new SelfPostSubmission(title, submission.getUrl(), content);
        return selfPostSubmission;
    }

    private static String cleanImgurUrl(String url) {
        try {
            URI uri = new URI(url);
            url = "http://i.imgur.com/" + uri.getPath() + ".jpg";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String cleanTitle(String title) {
        title = title.replaceAll("\\[.*\\]", "");
        return title;
    }
}
