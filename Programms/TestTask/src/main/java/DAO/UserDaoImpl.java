package DAO;

import models.User;
import utils.DbWrapper;

import java.sql.*;

/**
 * Created by ruslan on 07.10.2017.
 */
public class UserDaoImpl implements UserDao {

    private static UserDao userDao;

    private UserDaoImpl(){

    }


    public static UserDao getInstance(){
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }


    @Override
    public boolean isAuthorized(String username, String password) {
        Connection connection = null;
        try {
            connection = DbWrapper.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Statement statement = null;
        ResultSet rs  = null;
        String sql = null;
        try {
             statement = connection.createStatement();
              sql = "SELECT * FROM USERS WHERE username = '"
                 + username + "' AND password = '" + password + "'";

         } catch(Exception e) {
            e.printStackTrace();
         }

        try {
            rs = statement.executeQuery(sql);
            rs.next();
            if(rs.getString(1) != null && rs.getString(2) != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         return false;
    }

    @Override
    public void register(User user) {
        Connection connection = null;
        try {
            connection = DbWrapper.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO USERS VALUES (?,?)");
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isExists(String username) {
        Connection connection = null;

        try{
            connection = DbWrapper.getConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = null;
        try{
            statement = connection.createStatement();
            sql = "SELECT * FROM USERS WHERE username = '"
                    + username + "'";
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            if(resultSet.getString(1) != null && resultSet.getString(2) != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
