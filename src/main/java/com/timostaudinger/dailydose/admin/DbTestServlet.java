package com.timostaudinger.dailydose.admin;

import com.timostaudinger.dailydose.database.Database;
import com.timostaudinger.dailydose.model.User;
import org.hibernate.Session;

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

        Session session = Database.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> users = (List<User>) session.createQuery("from com.timostaudinger.dailydose.model.User").list();
        session.getTransaction().commit();

        response.getWriter().println(users.size());

        for (User user : users) {
            response.getWriter().println("User " + user.getId() + " - " + user.getName() + " - " + user.getEmail() + " - " + user.getFrequency() + " - " + user.getCreatedOn() + " - " + user.getChangedOn());
        }

    }
}
