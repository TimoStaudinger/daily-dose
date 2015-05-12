package com.timostaudinger.dailydose.util;

public class Version {

    public static String get() {
        return Properties.get("app_version");
    }

}
