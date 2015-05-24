package com.timostaudinger.dailydose.reddit;

import com.timostaudinger.dailydose.exception.RedditLoadException;
import com.timostaudinger.dailydose.util.Frequency;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;

public class Subreddit {

    private Subreddit() {
    }

    public static SubredditPaginator getPaginator(String subredditName, int limit, Frequency frequency, Sorting sorting) {
        SubredditPaginator subreddit = new SubredditPaginator(RedditClient.getInstance());

        subreddit.setSubreddit(subredditName);
        subreddit.setLimit(limit);
        subreddit.setTimePeriod(mapFrequencyToTimePeriod(frequency));
        subreddit.setSorting(sorting);

        return subreddit;
    }

    public static Submission getTopOf(String subreddit, Frequency frequency) throws RedditLoadException {
        SubredditPaginator paginator = getPaginator(subreddit, 1, frequency, Sorting.TOP);
        Listing<Submission> listing = paginator.next();
        if (listing.size() < 1) {
            throw new RedditLoadException();
        }

        return listing.get(0);
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
