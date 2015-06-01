package com.timostaudinger.dailydose.admin;

import com.timostaudinger.dailydose.exception.MailException;
import com.timostaudinger.dailydose.mail.Mailer;
import com.timostaudinger.dailydose.model.dao.UserDAO;
import com.timostaudinger.dailydose.model.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "mailtest", urlPatterns = {"/test/mail"})
public class MailTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = new UserDAO().findAll();

        response.getWriter().println(users.size());

        for (User user : users) {
            response.getWriter().println("User " + user.getId() + " - " + user.getName() + " - " + user.getEmail() + " - " + user.getFrequency() + " - " + user.getCreatedOn() + " - " + user.getChangedOn());
        }

        response.getWriter().println("\nSending mail...");

        try {
            new Mailer().sendHtmlMail("Your DailyDose", "<b>Test</b>", users);
        } catch (MailException e) {
            response.getWriter().println(e.getMessage());
        }

    }
}
