package com.ontheroad.service;

import com.ontheroad.intf.DemoHTTPHeaderNames;

import javax.security.auth.login.LoginException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Kouinou on 24/11/2015.
 */
public final class DemoAuthenticator {

    private static DemoAuthenticator INSTANCE = null;

    //Table <username, psw>
    private final Map<String, String> usersStorage = new HashMap();

    //Tablr <service_key, username>
    private final Map<String, Integer> idStorage = new HashMap();

    //Table <idUser, auth_token>
    private final Map<String, Integer> authorizationTokensStorage = new HashMap();

    private DemoAuthenticator(){

        // Pour les tests: A changer par la suite
        usersStorage.put("username1", "password1");
        usersStorage.put("username2", "password2");
        usersStorage.put("username3", "password3");

        idStorage.put( "username1", 1 );
        idStorage.put( "username2", 2 );
    }

    public static DemoAuthenticator getInstance(){

        if(INSTANCE == null){
            INSTANCE = new DemoAuthenticator();
        }

        return INSTANCE;
    }

    public Map<String, String> login(String username, String password ) throws LoginException {


            if ( usersStorage.containsKey( username )) {
                String passwordMatch = usersStorage.get( username );

                if ( passwordMatch.equals(password) ) {

                    /**
                     * Once all params are matched, the authToken will be
                     * generated and will be stored in the
                     * authorizationTokensStorage. The authToken will be needed
                     * for every REST API invocation and is only valid within
                     * the login session
                     */
                    String authToken = UUID.randomUUID().toString();
                    authorizationTokensStorage.put( authToken, idStorage.get(username));

                    Map<String, String> tempTokensStorage = new HashMap();

                    tempTokensStorage.put(DemoHTTPHeaderNames.AUTH_TOKEN, authToken);
                    tempTokensStorage.put(DemoHTTPHeaderNames.USERID, idStorage.get(username).toString());


                    return tempTokensStorage;
                }
            }


        throw new LoginException( "Don't Come Here Again!" );
    }

    /**
     * The method that pre-validates if the client which invokes the REST API is
     * from a authorized and authenticated source.
     *
     * @param userId
     * @param authToken The authorization token generated after login
     * @return TRUE for acceptance and FALSE for denied.
     */
    public boolean isAuthTokenValid(int userId, String authToken ) {



            if ( authorizationTokensStorage.containsKey(authToken) ) {
                int userIdMatch = authorizationTokensStorage.get( authToken );

                if ( userIdMatch == userId) {
                    return true;
                }
            }


        return false;
    }

    /**
     * This method checks is the service key is valid
     *
     *@param userId
     * @param authToken
     * @return TRUE if service key matches the pre-generated ones in service key
     * storage. FALSE for otherwise.
     */


    public void logout(int userId, String authToken ) throws GeneralSecurityException {


            if ( authorizationTokensStorage.containsKey( authToken ) ) {
                int userIdMatch = authorizationTokensStorage.get( authToken );

                if ( userIdMatch == userId ) {

                    /**
                     * When a client logs out, the authentication token will be
                     * remove and will be made invalid.
                     */
                    authorizationTokensStorage.remove( authToken );
                    return;
                }
            }


        throw new GeneralSecurityException( "Invalid service key and authorization token match." );
    }
}
