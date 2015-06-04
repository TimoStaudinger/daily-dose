package com.timostaudinger.dailydose.render;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

public class MailRenderer {

    private static final String CONTENT = "content";

    private Map<String, String> content;

    public MailRenderer(Map<String, String> content) {
        this.content = content;
    }

    public String renderMail(String templateName) {

        VelocityEngine velocityEngine = new VelocityEngine(getVelocityProperties());
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(templateName);

        StringWriter stringWriter = new StringWriter();
        template.merge(createContext(), stringWriter);

        return stringWriter.toString();
    }

    private Properties getVelocityProperties() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return properties;
    }

    private VelocityContext createContext() {

        VelocityContext context = new VelocityContext();
        context.put(CONTENT, content);

        return context;
    }
}
