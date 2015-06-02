package com.timostaudinger.dailydose.admin;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

@WebServlet(name = "velocitytest", urlPatterns = {"/test/velocity"})
public class MailRenderTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine velocityEngine = new VelocityEngine(props);

        VelocityContext context = new VelocityContext();

        velocityEngine.init();

        Template template = velocityEngine.getTemplate("dailydose.vm");
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        response.getWriter().print(stringWriter.toString());
    }
}
