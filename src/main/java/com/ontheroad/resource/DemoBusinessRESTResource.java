package com.ontheroad.resource;

import com.google.gson.Gson;
import com.ontheroad.intf.DemoBusinessRESTResourceProxy;
import com.ontheroad.intf.DemoHTTPHeaderNames;
import com.ontheroad.model.Person;
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
import java.util.HashMap;
import java.util.Map;

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


        try {
            Map<String, String> authToken = demoAuthenticator.login(username, password);

            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            jsonObjBuilder.add(DemoHTTPHeaderNames.USERID, Integer.parseInt(authToken.get(DemoHTTPHeaderNames.USERID)));
            jsonObjBuilder.add(DemoHTTPHeaderNames.AUTH_TOKEN, authToken.get(DemoHTTPHeaderNames.AUTH_TOKEN));

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
    @Produces( MediaType.APPLICATION_JSON )
    public Response demoGetMethod(@Context HttpHeaders httpHeaders) {


        Person p = new Person();
        p.setFirstName("Nabi");
        p.setLastName("Zamani");
        //p.setDateOfBirth("01.01.2012");

        p.setCitizenships( new String[]{"German", "Persian"} );


        Map<String, Object> creditCards = new HashMap<String, Object>();
        creditCards.put("MasterCard", "1234 1234 1234 1234");
        creditCards.put("Visa", "1234 1234 1234 1234");
        creditCards.put("dummy", true);
        p.setCreditCards(creditCards);

        System.out.println("REST call...");



        //return Response.ok().entity(p).build();
        //return p;

        Gson gson = new Gson();

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(p);
        return getNoCacheResponseBuilder( Response.Status.OK ).entity( json ).build();
    }

    @Override
    public Response demoPostMethod(@Context HttpHeaders httpHeaders) {
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
            String userID = httpHeaders.getHeaderString(DemoHTTPHeaderNames.USERID);
            String authToken = httpHeaders.getHeaderString( DemoHTTPHeaderNames.AUTH_TOKEN );

            demoAuthenticator.logout( Integer.parseInt(userID), authToken );

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
