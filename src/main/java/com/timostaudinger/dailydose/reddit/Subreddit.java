package com.timostaudinger.dailydose.reddit;

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

}
