package com.timostaudinger.dailydose.businesslayer;

import com.timostaudinger.dailydose.model.dao.UserDAO;
import com.timostaudinger.dailydose.model.dto.User;
import com.timostaudinger.dailydose.util.Frequency;

public class SubscriberTools {

    private SubscriberTools() {
    }

    public static boolean subscribe(String email) {

        User user = new User(email, Frequency.DAILY, true, null);

        return new UserDAO().create(user);
    }
}
