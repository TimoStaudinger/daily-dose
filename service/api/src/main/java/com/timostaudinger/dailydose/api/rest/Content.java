package com.timostaudinger.dailydose.api.rest;

import com.timostaudinger.dailydose.api.model.dto.ContentResponse;
import com.timostaudinger.dailydose.common.model.dao.RedditDAO;
import com.timostaudinger.dailydose.common.model.dao.SubmissionMapper;
import com.timostaudinger.dailydose.common.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.common.model.dto.SelfpostSubmission;
import com.timostaudinger.dailydose.common.util.Frequency;

public class Content {
    private  static final Frequency FREQUENCY = Frequency.DAILY;
    private static final String SUBREDDIT = System.getenv("SUBREDDIT");

    public static ContentResponse getContent() {
        ImageSubmission imageSubmission = SubmissionMapper.mapImageSubmission(RedditDAO.getTopImageOf(SUBREDDIT, FREQUENCY));
        SelfpostSubmission selfpostSubmission = SubmissionMapper.mapSelfpostSubmission(RedditDAO.getTopSelfPostOf(SUBREDDIT, FREQUENCY));

        return new ContentResponse(imageSubmission, selfpostSubmission);
    }
}
