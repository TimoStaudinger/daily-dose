package com.timostaudinger.dailydose.rest;

import com.timostaudinger.dailydose.businesslayer.SubscriberTools;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("subscribe")
public class SubscriberResource {

    @GET
    @Path("/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("email") String email) {
        return String.valueOf(SubscriberTools.subscribe(email) ? 1 : 0);
    }

}
