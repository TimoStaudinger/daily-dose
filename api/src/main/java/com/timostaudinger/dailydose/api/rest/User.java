package com.timostaudinger.dailydose.api.rest;

import com.timostaudinger.dailydose.api.model.dto.Response;
import com.timostaudinger.dailydose.api.model.dto.UserRequest;
import com.timostaudinger.dailydose.common.model.dao.UserDAO;

public class User {
    public static Response subscribe(UserRequest request) {
        new UserDAO().subscribe(request.getEmail());

        return new Response("Successfully subscribed", true);
    }

    public static Response unsubscribe(UserRequest request) {
        new UserDAO().unsubscribe(request.getEmail());

        return new Response("Successfully unsubscribed", true);
    }
}
