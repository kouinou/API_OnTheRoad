package com.ontheroad.intf;

import com.ontheroad.model.Person;

import javax.ws.rs.*;
import javax.ejb.Local;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Kouinou on 24/11/2015.
 */
@Local
@Path( "demo-business-resource")
public interface DemoBusinessRESTResourceProxy extends Serializable {

    @POST
    @Path( "login" )
    @Produces(MediaType.APPLICATION_JSON)
    Response login(
            @Context HttpHeaders httpHeaders,
            @FormParam("username") String username,
            @FormParam("password") String password);

    @GET
    @Path( "demo-get-method" )
    @Produces( MediaType.APPLICATION_JSON )
    Response demoGetMethod(@Context HttpHeaders httpHeaders);

    @POST
    @Path( "demo-post-method" )
    @Produces( MediaType.APPLICATION_JSON )
    Response demoPostMethod(@Context HttpHeaders httpHeaders);

    @POST
    @Path( "logout" )
    Response logout(@Context HttpHeaders httpHeaders);

}
