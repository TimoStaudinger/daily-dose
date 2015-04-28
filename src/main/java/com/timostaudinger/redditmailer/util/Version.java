package com.timostaudinger.redditmailer.util;

public class Version {

    public static String get() {
        return Properties.get("app_version");
    }

}
