package com.timostaudinger.dailydose.model.dao;

import com.timostaudinger.dailydose.exception.RedditAuthException;
import com.timostaudinger.dailydose.exception.RedditLoadException;
import com.timostaudinger.dailydose.util.Frequency;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;

import java.net.MalformedURLException;
import java.net.URL;

public class RedditDAO {

    private RedditDAO() {
    }

    public static SubredditPaginator getPaginatorOf(String subredditName, int limit, Frequency frequency, Sorting sorting) throws RedditAuthException {
        SubredditPaginator subreddit = new SubredditPaginator(RedditClient.getInstance());

        subreddit.setSubreddit(subredditName);
        subreddit.setLimit(limit);
        subreddit.setTimePeriod(mapFrequencyToTimePeriod(frequency));
        subreddit.setSorting(sorting);

        return subreddit;
    }

    public static Submission getTopOf(String subreddit, Frequency frequency) throws RedditLoadException, RedditAuthException {
        SubredditPaginator paginator = getPaginatorOf(subreddit, 1, frequency, Sorting.TOP);
        Listing<Submission> listing = paginator.next();
        if (listing.size() < 1) {
            throw new RedditLoadException("Could not load Submissions for " + subreddit + " with frequency " + frequency.name());
        }

        return listing.get(0);
    }

    public static Submission getTopSelfPostOf(String subreddit, Frequency frequency) throws RedditLoadException, RedditAuthException {
        SubredditPaginator paginator = getPaginatorOf(subreddit, 10, frequency, Sorting.TOP);
        while (paginator.hasNext()) {
            Listing<Submission> listing = paginator.next();
            for (Submission submission : listing) {
                if (submission.isSelfPost()) {
                    return submission;
                }
            }
        }
        throw new RedditLoadException("Top Self Post not found");
    }

    public static Submission getTopImageOf(String subreddit, Frequency frequency) throws RedditLoadException, RedditAuthException {
        SubredditPaginator paginator = getPaginatorOf(subreddit, 10, frequency, Sorting.TOP);
        while (paginator.hasNext()) {
            Listing<Submission> listing = paginator.next();
            for (Submission submission : listing) {
                try {
                    if (new URL(submission.getUrl()).getHost().contains("imgur.com")) {
                        return submission;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

        throw new RedditLoadException("Top Image not found");
    }

    private static TimePeriod mapFrequencyToTimePeriod(Frequency frequency) {
        switch (frequency) {
            case DAILY:
                return TimePeriod.DAY;
            case WEEKLY:
                return TimePeriod.DAY;
            case MONTHLY:
                return TimePeriod.MONTH;
            default:
                return TimePeriod.DAY;
        }
    }

}
