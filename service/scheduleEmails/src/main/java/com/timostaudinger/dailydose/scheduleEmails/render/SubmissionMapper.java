package com.timostaudinger.dailydose.scheduleEmails.render;

import com.timostaudinger.dailydose.scheduleEmails.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.scheduleEmails.model.dto.SelfpostSubmission;
import net.dean.jraw.models.Submission;

import java.net.URI;
import java.net.URISyntaxException;

public class SubmissionMapper {
    public static ImageSubmission mapImageSubmission(Submission submission) {

        String title = cleanTitle(submission.getTitle());
        String imageUrl = cleanImgurUrl(submission.getUrl());

        return new ImageSubmission(title, submission.getUrl(), imageUrl);
    }

    public static SelfpostSubmission mapSelfpostSubmission(Submission submission) {

        String title = cleanTitle(submission.getTitle());
        String content = cleanContent(submission.getSelftext());

        return new SelfpostSubmission(title, submission.getUrl(), content);
    }

    private static String cleanContent(String content) {
        content = content.replaceAll("\n", "<br>");
        return content;
    }

    private static String cleanImgurUrl(String url) {
        try {
            URI uri = new URI(url);
            url = "http://i.imgur.com/" + uri.getPath() + ".jpg";
        } catch (URISyntaxException e) {
            // TODO: logging
            e.printStackTrace();
        }
        return url;
    }

    private static String cleanTitle(String title) {
        title = title.replaceAll("\\[.*\\]", "");
        return title;
    }
}
