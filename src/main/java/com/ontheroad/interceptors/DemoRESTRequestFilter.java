package com.ontheroad.interceptors;

import com.ontheroad.dao.UtilisateurDao;
import com.ontheroad.intf.DemoHTTPHeaderNames;
import com.ontheroad.service.DemoAuthenticator;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.Console;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Kouinou on 24/11/2015.
 */
@Provider
@PreMatching
public class DemoRESTRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger(DemoRESTRequestFilter.class.getName());

    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {

        requestCtx.getHeaders().add( "Access-Control-Allow-Origin", "*" );
        String path = requestCtx.getUriInfo().getPath();
        log.info( "Filtering request path: " + path );

        // IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for this case before validating the headers (CORS stuff)
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
            requestCtx.abortWith( Response.status(Response.Status.OK).build() );

           return;
        }

        // Then check is the service key exists and is valid.
        UtilisateurDao utilisateurDao = UtilisateurDao.getINSTANCE();
        //String username = requestCtx.getHeaderString( DemoHTTPHeaderNames.USERNAME );



        // For any pther methods besides login, the authToken must be verified

        if ( !path.startsWith( "demo-business-resource/login" ) ) {
            String user_id = requestCtx.getHeaderString( "user_id" );
            String authToken = requestCtx.getHeaderString( "auth_token" );

            // if it isn't valid, just kick them out.
            if ( !utilisateurDao.isAuthTokenValid( Integer.parseInt(user_id), authToken ) ) {
                requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
            }
        }
    }
}
