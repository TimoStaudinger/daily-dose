package com.timostaudinger.dailydose.reddit;

import com.timostaudinger.dailydose.util.Properties;
import com.timostaudinger.dailydose.util.Version;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.LoggingMode;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;

public class Subreddit {

    private static final String PLATFORM = Properties.get("reddit_platform");
    private static final String APP_ID = Properties.get("reddit_app_id");
    private static final String VERSION = Version.get();
    private static final String USERNAME = Properties.get("reddit_username");
    private static final String PASSWORD = Properties.get("reddit_password");
    private static final String CLIENT_ID = Properties.get("reddit_client_id");
    private static final String CLIENT_SECRET = Properties.get("reddit_client_secret");
    private static RedditClient redditClient;

    private Subreddit() {
    }

    public static SubredditPaginator getPaginator(String subredditName, int limit, TimePeriod timePeriod, Sorting sorting) {
        SubredditPaginator subreddit = new SubredditPaginator(getRedditClient());
        subreddit.setSubreddit(subredditName);
        subreddit.setLimit(limit);
        subreddit.setTimePeriod(timePeriod);
        subreddit.setSorting(sorting);

        return subreddit;
    }

    private static RedditClient getRedditClient() {
        if (redditClient == null) {

            UserAgent userAgent = UserAgent.of(PLATFORM, APP_ID, VERSION, USERNAME);
            Credentials credentials = Credentials.script(USERNAME, PASSWORD, CLIENT_ID, CLIENT_SECRET);
            redditClient = new RedditClient(userAgent);
            redditClient.setLoggingMode(LoggingMode.ON_FAIL);
            try {
                OAuthData authData = redditClient.getOAuthHelper().easyAuth(credentials);
                redditClient.authenticate(authData);
            } catch (OAuthException e) {
                // TODO: Handle exception
            }
        }
        return redditClient;
    }

}
