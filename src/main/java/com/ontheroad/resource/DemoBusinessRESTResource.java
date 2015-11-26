package com.ontheroad.resource;

import com.ontheroad.intf.DemoBusinessRESTResourceProxy;
import com.ontheroad.intf.DemoHTTPHeaderNames;
import com.ontheroad.service.DemoAuthenticator;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.security.auth.login.LoginException;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.security.GeneralSecurityException;

/**
 * Created by Kouinou on 24/11/2015.
 */

@Stateless( name = "DemoBusinessRESTResource", mappedName = "ejb/DemoBusinessRESTResource" )
public class DemoBusinessRESTResource implements DemoBusinessRESTResourceProxy {

    private static final long serialVersionUID = -6663599014192066936L;


    @Override
    public Response login(
            @Context HttpHeaders httpHeaders,
            @FormParam( "username" ) String username,
            @FormParam( "password" ) String password ) {

        DemoAuthenticator demoAuthenticator = DemoAuthenticator.getInstance();
        String serviceKey = httpHeaders.getHeaderString(DemoHTTPHeaderNames.SERVICE_KEY);

        try {
            String authToken = demoAuthenticator.login(serviceKey, username, password);

            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            jsonObjBuilder.add("auth_token", authToken);
            JsonObject jsonObj = jsonObjBuilder.build();

            return getNoCacheResponseBuilder(Response.Status.OK).entity(jsonObj.toString()).build();

        } catch (final LoginException ex) {
            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            jsonObjBuilder.add("message", "Problem matching service key, username and password");
            JsonObject jsonObj = jsonObjBuilder.build();

            return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).entity(jsonObj.toString()).build();
        }

    }

    @Override
    public Response demoGetMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add( "message", "Executed demoGetMethod" );
        JsonObject jsonObj = jsonObjBuilder.build();

        return getNoCacheResponseBuilder( Response.Status.OK ).entity( jsonObj.toString() ).build();
    }

    @Override
    public Response demoPostMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add( "message", "Executed demoPostMethod" );
        JsonObject jsonObj = jsonObjBuilder.build();

        return getNoCacheResponseBuilder( Response.Status.ACCEPTED ).entity( jsonObj.toString() ).build();
    }

    @Override
    public Response logout(
            @Context HttpHeaders httpHeaders ) {
        try {
            DemoAuthenticator demoAuthenticator = DemoAuthenticator.getInstance();
            String serviceKey = httpHeaders.getHeaderString( DemoHTTPHeaderNames.SERVICE_KEY );
            String authToken = httpHeaders.getHeaderString( DemoHTTPHeaderNames.AUTH_TOKEN );

            demoAuthenticator.logout( serviceKey, authToken );

            return getNoCacheResponseBuilder( Response.Status.NO_CONTENT ).build();
        } catch ( final GeneralSecurityException ex ) {
            return getNoCacheResponseBuilder( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
            CacheControl cc = new CacheControl();
            cc.setNoCache( true );
            cc.setMaxAge( -1 );
            cc.setMustRevalidate( true );

            return Response.status( status ).cacheControl( cc );
        }


}
