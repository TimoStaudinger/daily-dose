package com.timostaudinger.dailydose.api.rest;

import com.timostaudinger.dailydose.api.model.dto.UserResponse;
import com.timostaudinger.dailydose.api.model.dto.UserRequest;
import com.timostaudinger.dailydose.common.model.dao.UserDAO;

public class User {
    public static UserResponse subscribe(UserRequest request) {
        new UserDAO().subscribe(request.getEmail());

        return new UserResponse("Successfully subscribed", true);
    }

    public static UserResponse unsubscribe(UserRequest request) {
        new UserDAO().unsubscribe(request.getEmail());

        return new UserResponse("Successfully unsubscribed", true);
    }
}
