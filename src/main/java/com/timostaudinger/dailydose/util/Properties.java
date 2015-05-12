package com.timostaudinger.dailydose.util;

import java.io.IOException;
import java.io.InputStream;

public class Properties {

    private static java.util.Properties properties;

    private Properties() {
    }

    private static java.util.Properties getProperties() {
        if (properties == null) {
            try {
                InputStream in = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("config.properties");
                properties = new java.util.Properties();
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
                // TODO: Handle exception
            }
        }
        return properties;
    }

    public static String get(String key) {
        return getProperties().getProperty(key);
    }
}
