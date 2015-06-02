package com.timostaudinger.dailydose.render;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MailRenderer {

    private static final String IMAGE_TITLE = "imageTitle";
    private static final String IMAGE_URL = "imageUrl";
    private static final String QUOTE_TITLE = "quoteTitle";
    private static final String QUOTE_CONTENT = "quoteContent";
    private static final String CONTENT = "content";
    private static final String TEMPLATE_NAME = "dailydose.vm";
    private String imageTitle;
    private String imageUrl;
    private String quoteTitle;
    private String quoteContent;

    public MailRenderer(String imageTitle, String imageUrl, String quoteTitle, String quoteContent) {
        this.imageTitle = imageTitle;
        this.imageUrl = imageUrl;
        this.quoteTitle = quoteTitle;
        this.quoteContent = quoteContent;
    }

    public String renderMail() {

        VelocityEngine velocityEngine = new VelocityEngine(getVelocityProperties());
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(TEMPLATE_NAME);

        StringWriter stringWriter = new StringWriter();
        template.merge(mapFields(), stringWriter);

        return stringWriter.toString();
    }

    private Properties getVelocityProperties() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return properties;
    }

    private VelocityContext mapFields() {
        Map content = new HashMap();
        content.put(IMAGE_TITLE, imageTitle);
        content.put(IMAGE_URL, imageUrl);
        content.put(QUOTE_TITLE, quoteTitle);
        content.put(QUOTE_CONTENT, quoteContent);

        VelocityContext context = new VelocityContext();
        context.put(CONTENT, content);

        return context;
    }
}
