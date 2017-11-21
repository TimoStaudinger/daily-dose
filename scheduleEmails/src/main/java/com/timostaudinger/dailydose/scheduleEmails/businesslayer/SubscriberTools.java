package com.timostaudinger.dailydose.scheduleEmails.businesslayer;

import com.timostaudinger.dailydose.common.model.dao.UserDAO;
import com.timostaudinger.dailydose.common.model.dto.User;
import com.timostaudinger.dailydose.common.util.ValidationTools;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriberTools {

    private SubscriberTools() {
    }

//    public static boolean subscribe(String email) {
//
//        User user = new User(email, Frequency.DAILY, true, null);
//
//        return new UserDAO().create(user);
//    }

//    public static boolean find(String email) {
//
//        return new UserDAO().find(email) != null;
//    }

    public static List<User> findAllActive() {
        List<User> subscribers = new UserDAO().findAllActive();

        return subscribers.stream().filter(u -> ValidationTools.validateEmail(u.getEmail())).collect(Collectors.toList());
    }
}
