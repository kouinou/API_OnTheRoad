package com.ontheroad.interceptors;

import com.ontheroad.intf.DemoHTTPHeaderNames;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Kouinou on 24/11/2015.
 */

@Provider
@PreMatching
public class DemoRESTResponseFilter implements ContainerResponseFilter {

    private final static Logger log = Logger.getLogger( DemoRESTResponseFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {

        log.info( "Filtering REST Response" );

        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "*" );    // You may further limit certain client IPs with Access-Control-Allow-Origin instead of '*'
        responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
        //responseCtx.getHeaders().add( "Access-Control-Allow-Headers", "*" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Headers", "user_id, auth_token, username, password" );
    }
}
