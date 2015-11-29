package com.ontheroad.resource;

import com.google.gson.Gson;
import com.ontheroad.model.Person;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kouinou on 24/11/2015.
 */
@Local
@Path("/person")
@Stateless( name = "PersonResource", mappedName = "ejb/PersonResource" )
public class PersonResource {

    @Path("/get")
    @GET
    @Produces({MediaType.APPLICATION_JSON})  //add MediaType.APPLICATION_XML if you want XML as well (don't forget @XmlRootElement)
    public Response getPerson(){

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

        Gson gson = new Gson();

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(p);
        return getNoCacheResponseBuilder(Response.Status.OK).entity( json ).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/post")
    public String postPerson(Person pers) throws Exception{

        System.out.println("First Name = "+pers.getFirstName());
        System.out.println("Last Name  = "+pers.getLastName());

        return "ok";
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
        CacheControl cc = new CacheControl();
        cc.setNoCache( true );
        cc.setMaxAge( -1 );
        cc.setMustRevalidate( true );

        return Response.status( status ).cacheControl( cc );
    }
}
