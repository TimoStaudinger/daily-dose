package com.timostaudinger.dailydose.reddit;

import com.timostaudinger.dailydose.exception.RedditLoadException;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;

public class Subreddit {

    private Subreddit() {
    }

    public static SubredditPaginator getPaginator(String subredditName, int limit, TimePeriod timePeriod, Sorting sorting) {
        SubredditPaginator subreddit = new SubredditPaginator(RedditClient.getInstance());

        subreddit.setSubreddit(subredditName);
        subreddit.setLimit(limit);
        subreddit.setTimePeriod(timePeriod);
        subreddit.setSorting(sorting);

        return subreddit;
    }

    public static Submission getDailyTopOf(String subreddit) throws RedditLoadException {
        SubredditPaginator paginator = getPaginator(subreddit, 1, TimePeriod.DAY, Sorting.TOP);
        Listing<Submission> listing = paginator.next();
        if (listing.size() < 1) {
            throw new RedditLoadException();
        }

        return listing.get(0);
    }

}
