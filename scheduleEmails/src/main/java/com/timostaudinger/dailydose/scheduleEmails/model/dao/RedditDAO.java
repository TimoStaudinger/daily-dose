package com.timostaudinger.dailydose.scheduleEmails.model.dao;

import com.timostaudinger.dailydose.scheduleEmails.exception.RedditAuthException;
import com.timostaudinger.dailydose.scheduleEmails.exception.RedditLoadException;
import com.timostaudinger.dailydose.scheduleEmails.util.Frequency;
import com.timostaudinger.dailydose.scheduleEmails.util.FrequencyUtils;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public final class RedditDAO {

    private RedditDAO() {
    }

    private static SubredditPaginator getPaginatorOf(String subredditName, int limit, Frequency frequency, Sorting sorting) throws RedditAuthException {
        SubredditPaginator subreddit = new SubredditPaginator(RedditClientBuilder.createRedditClient());

        subreddit.setSubreddit(subredditName);
        subreddit.setLimit(limit);
        subreddit.setTimePeriod(FrequencyUtils.mapFrequencyToTimePeriod(frequency));
        subreddit.setSorting(sorting);

        return subreddit;
    }

    public static Submission getTopSelfPostOf(String subreddit, Frequency frequency) throws RedditLoadException, RedditAuthException {
        Frequency currentFrequency = frequency;
        do {
            SubredditPaginator paginator = getPaginatorOf(subreddit, 100, currentFrequency, Sorting.TOP);
            while (paginator.hasNext()) {
                Optional<Submission> submission = paginator.next().stream().filter(Submission::isSelfPost).findFirst();
                if (submission.isPresent()) {
                    return submission.get();
                }
            }
        } while ((currentFrequency = FrequencyUtils.getNextHigher(currentFrequency)) != null);
        throw new RedditLoadException("Top Self Post not found");
    }

    public static Submission getTopImageOf(String subreddit, Frequency frequency) throws RedditLoadException, RedditAuthException {
        Frequency currentFrequency = frequency;
        do {
            SubredditPaginator paginator = getPaginatorOf(subreddit, 100, currentFrequency, Sorting.TOP);
            while (paginator.hasNext()) {
                Optional<Submission> submission = paginator.next().stream().filter(RedditDAO::isHostImgur).findFirst();
                if (submission.isPresent()) {
                    return submission.get();
                }
            }
        } while ((currentFrequency = FrequencyUtils.getNextHigher(currentFrequency)) != null);
        throw new RedditLoadException("Top Image not found");
    }

    private static boolean isHostImgur(Submission submission) {
        try {
            return new URL(submission.getUrl()).getHost().contains("imgur.com");
        } catch (MalformedURLException e) {
            // TODO: logging
            e.printStackTrace();
        }
        return false;
    }

}
