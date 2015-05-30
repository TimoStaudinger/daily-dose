package com.timostaudinger.dailydose.admin;

import com.timostaudinger.dailydose.model.dao.UserDAO;
import com.timostaudinger.dailydose.model.dto.Token;
import com.timostaudinger.dailydose.model.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "dbtest", urlPatterns = {"/test/db"})
public class DbTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = new UserDAO().findAll();

        response.getWriter().println(users.size());

        for (User user : users) {
            response.getWriter().println("User " + user.getId() + " - " + user.getName() + " - " + user.getEmail() + " - " + user.getFrequency() + " - " + user.getCreatedOn() + " - " + user.getChangedOn());
            for (Token token : user.getTokens()) {
                response.getWriter().println(" -- " + token.getUuid() + " - " + token.isUsed() + " - " + token.getChangedOn() + " - " + token.getCreatedOn());
            }
        }

    }
}
