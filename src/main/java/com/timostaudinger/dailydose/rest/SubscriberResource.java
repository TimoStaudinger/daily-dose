package com.timostaudinger.dailydose.rest;

import com.timostaudinger.dailydose.businesslayer.SubscriberTools;
import com.timostaudinger.dailydose.util.ValidationTools;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class SubscriberResource {

    @GET
    @Path("/subscribe/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public String subscribe(@PathParam("email") String email) {
        boolean result = true;
        result = result && ValidationTools.validateEmail(email);

        return (result && SubscriberTools.subscribe(email)) ? "1" : "0";
    }

    @GET
    @Path("/find/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public String find(@PathParam("email") String email) {
        boolean result = true;
        result = result && ValidationTools.validateEmail(email);

        return (result && SubscriberTools.find(email)) ? "1" : "0";
    }


}
