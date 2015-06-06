package com.timostaudinger.dailydose.model.dao;

import com.timostaudinger.dailydose.util.Frequency;
import net.dean.jraw.models.Submission;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RedditDAOTest {
    @Test
    public void testTopSelfpost() throws Exception {
        Submission submission = RedditDAO.getTopSelfPostOf("getmotivated", Frequency.DAILY);
        assertNotNull(submission);
        assertTrue(submission.getTitle().length() > 0);
    }

    @Test
    public void testTopImage() throws Exception {
        Submission submission = RedditDAO.getTopImageOf("getmotivated", Frequency.DAILY);
        assertNotNull(submission);
        assertTrue(submission.getTitle().length() > 0);
    }

}
