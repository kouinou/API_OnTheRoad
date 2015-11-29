package com.ontheroad.dao;

import com.ontheroad.intf.DemoHTTPHeaderNames;

import javax.security.auth.login.LoginException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Kouinou on 24/11/2015.
 */
public class UtilisateurDao {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/on_the_road";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static UtilisateurDao INSTANCE = null;

    public UtilisateurDao() {

    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("Can not load Driver", e);
        }
    }

    public static UtilisateurDao getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new UtilisateurDao();
        }
        return INSTANCE;
    }

    public Map<String, String> login(String username, String password ) throws LoginException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int resultSet2 = 0;
        int id;
        Map<String, String> tempTokensStorage = null;

        try{

            String sql;
            //sql = "SELECT * FROM utilisateur;";
            sql = "SELECT id_utilisateur AS id_util FROM utilisateur WHERE user = '" + username + "' AND password = '" + password + "';";

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();



            if (!resultSet.wasNull()){

                resultSet.next();
                id = resultSet.getInt("id_util");


                String authToken = UUID.randomUUID().toString();

                tempTokensStorage = new HashMap();

                try {
                    sql = "UPDATE utilisateur SET auth_token = '" + authToken + "' WHERE id_utilisateur = " + id + ";";
                    statement = connection.createStatement();
                    resultSet2 = statement.executeUpdate(sql);

                    tempTokensStorage.put(DemoHTTPHeaderNames.AUTH_TOKEN, authToken);
                    tempTokensStorage.put(DemoHTTPHeaderNames.USERID, Integer.toString(id));

                }catch(SQLException e) {

                    throw new DAOException("Impossible d'ajouter le token à la base", e);
                }finally {
                    DaoUtils.closeAll(null, statement, connection);
                }



            }
            else{
                throw new LoginException( "Don't Come Here Again!" );
            }

        }catch(SQLException e) {

            throw new DAOException("Impossible de récupérer l'identifiant de l'utilisateur", e);
        }finally {
            DaoUtils.closeAll(resultSet, preparedStatement, connection);
            return tempTokensStorage;
        }


    }

    public boolean isAuthTokenValid(int userId, String authToken ) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Boolean result = false;
        String sql;

        try{
            sql = "SELECT COUNT(*) AS nb_util FROM utilisateur WHERE id_utilisateur = " + userId + " AND auth_token = '" + authToken + "';";
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            if (resultSet.getInt("nb_util") > 0){
                result = true;
            }
        }catch(SQLException e) {

            throw new DAOException("Impossible de vérifier le token", e);
        }finally {
            DaoUtils.closeAll(resultSet, preparedStatement, connection);
        }


        return result;
    }






}
