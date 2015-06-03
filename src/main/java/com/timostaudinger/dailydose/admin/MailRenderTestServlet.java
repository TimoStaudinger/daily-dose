package com.timostaudinger.dailydose.admin;

import com.timostaudinger.dailydose.exception.MailException;
import com.timostaudinger.dailydose.exception.RedditAuthException;
import com.timostaudinger.dailydose.exception.RedditLoadException;
import com.timostaudinger.dailydose.mail.Mailer;
import com.timostaudinger.dailydose.model.dao.RedditDAO;
import com.timostaudinger.dailydose.model.dao.UserDAO;
import com.timostaudinger.dailydose.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.model.dto.SelfPostSubmission;
import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.render.RedditMailRenderer;
import com.timostaudinger.dailydose.render.SubmissionCleaner;
import com.timostaudinger.dailydose.util.Frequency;
import net.dean.jraw.models.Submission;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "mailrendertest", urlPatterns = {"/test/mailrender"})
public class MailRenderTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Submission quoteSubmission = RedditDAO.getTopSelfPostOf("getmotivated", Frequency.DAILY);
            SelfPostSubmission quote = SubmissionCleaner.cleanSelfPostSubmission(quoteSubmission);

            Submission imageSubmission = RedditDAO.getTopImageOf("getmotivated", Frequency.DAILY);
            ImageSubmission image = SubmissionCleaner.cleanImageSubmission(imageSubmission);

            String mail = new RedditMailRenderer(quote, image).render();

            List<User> users = new UserDAO().findAll();

            response.getWriter().println(users.size());

            for (User user : users) {
                response.getWriter().println("User " + user.getId() + " - " + user.getName() + " - " + user.getEmail() + " - " + user.getFrequency() + " - " + user.getCreatedOn() + " - " + user.getChangedOn());
            }

            response.getWriter().println("\nSending mail...");

            try {
                new Mailer().sendHtmlMail("Your DailyDose", mail, users);
                response.getWriter().println("\nSent");
            } catch (MailException e) {
                response.getWriter().println(e.getMessage());
            }

        } catch (RedditLoadException e) {
            e.printStackTrace();
        } catch (RedditAuthException e) {
            e.printStackTrace();
        }

    }
}
