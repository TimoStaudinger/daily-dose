package com.timostaudinger.dailydose.common.model.dao;

import com.timostaudinger.dailydose.common.exception.RedditAuthException;
import net.dean.jraw.http.LoggingMode;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;

import java.util.UUID;

final class RedditClientBuilder {
    private static final String PLATFORM = System.getenv("REDDIT_PLATFORM");
    private static final String APP_ID = System.getenv("REDDIT_APP_ID");
    private static final String APP_VERSION = System.getenv("REDDIT_APP_VERSION");
    private static final String USERNAME = System.getenv("REDDIT_USERNAME");
    private static final String CLIENT_ID = System.getenv("REDDIT_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("REDDIT_CLIENT_SECRET");

    private RedditClientBuilder() {
    }


    static net.dean.jraw.RedditClient createRedditClient() throws RedditAuthException {
        UserAgent userAgent = UserAgent.of(PLATFORM, APP_ID, APP_VERSION, USERNAME);
        Credentials credentials = Credentials.userless(CLIENT_ID, CLIENT_SECRET, UUID.randomUUID());
        net.dean.jraw.RedditClient redditClient = new net.dean.jraw.RedditClient(userAgent);
        redditClient.setLoggingMode(LoggingMode.ON_FAIL);

        try {
            OAuthData authData = redditClient.getOAuthHelper().easyAuth(credentials);
            redditClient.authenticate(authData);
        } catch (OAuthException e) {
            throw new RedditAuthException("Could not authorize application", e);
        }

        return redditClient;
    }
}
