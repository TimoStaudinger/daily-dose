package com.timostaudinger.dailydose.reddit;

import com.timostaudinger.dailydose.exception.RedditAuthException;
import com.timostaudinger.dailydose.util.Properties;
import com.timostaudinger.dailydose.util.Version;
import net.dean.jraw.http.LoggingMode;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;

public class RedditClient extends net.dean.jraw.RedditClient {

    private static final String PLATFORM = Properties.get("reddit_platform");
    private static final String APP_ID = Properties.get("reddit_app_id");
    private static final String VERSION = Version.get();
    private static final String USERNAME = Properties.get("reddit_username");
    private static final String PASSWORD = Properties.get("reddit_password");
    private static final String CLIENT_ID = Properties.get("reddit_client_id");
    private static final String CLIENT_SECRET = Properties.get("reddit_client_secret");

    private static RedditClient instance;

    private RedditClient(UserAgent userAgent) {
        super(userAgent);
    }

    public static RedditClient getInstance() throws RedditAuthException {
        if (instance == null) {
            instance = createRedditClient();
        }

        return instance;
    }

    private static RedditClient createRedditClient() throws RedditAuthException {
        UserAgent userAgent = UserAgent.of(PLATFORM, APP_ID, VERSION, USERNAME);
        Credentials credentials = Credentials.script(USERNAME, PASSWORD, CLIENT_ID, CLIENT_SECRET);
        RedditClient redditClient = new RedditClient(userAgent);
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
