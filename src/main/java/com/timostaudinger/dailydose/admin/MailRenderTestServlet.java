package com.timostaudinger.dailydose.admin;

import com.timostaudinger.dailydose.render.MailRenderer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mailrendertest", urlPatterns = {"/test/mailrender"})
public class MailRenderTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = new MailRenderer("Test", "http://i.imgur.com/WgYGR1z.jpg", "Bla", "Hello World").renderMail();
        response.getWriter().print(mail);
    }
}
