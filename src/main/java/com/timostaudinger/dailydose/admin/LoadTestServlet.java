package com.timostaudinger.dailydose.admin;

import com.timostaudinger.dailydose.exception.RedditAuthException;
import com.timostaudinger.dailydose.exception.RedditLoadException;
import com.timostaudinger.dailydose.model.dao.RedditDAO;
import com.timostaudinger.dailydose.util.Frequency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loadtest", urlPatterns = {"/test/load"})
public class LoadTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String dailyTop = RedditDAO.getTopSelfPostOf("getmotivated", Frequency.DAILY).getTitle();
            response.getWriter().println(dailyTop);
        } catch (RedditLoadException e) {
            response.getWriter().println(e.getMessage());
        } catch (RedditAuthException e) {
            response.getWriter().println(e.getMessage());
        }

    }
}
