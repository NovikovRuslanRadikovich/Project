package ru.itis.kpfu.Novikov_Ruslan.DAO;


import ru.itis.kpfu.Novikov_Ruslan.models.User;
import ru.itis.kpfu.Novikov_Ruslan.utils.DbWrapper;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl userDao;

    public static UserDaoImpl getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }

        return userDao;
    }

    private UserDaoImpl() {

    }


    public void saveUser(User user) throws SQLException {

        Connection connection = DbWrapper.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (login, password, gender, phone, dob, city) VALUES (?, ?, ?, ?, ?, ?);");

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPasswordHash());
        preparedStatement.setBoolean(3, user.isMale());
        preparedStatement.setString(4, user.getPhoneNumber());
        preparedStatement.setString(5, user.getDOB());
        preparedStatement.setString(6, user.getCity());


        preparedStatement.execute();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE \"" + user.getName() + "\" (id INTEGER NOT NULL)");
        } catch(SQLException ex) {
           ex.printStackTrace();
        }
            connection.close();
    }


    public User getUser(int userId) {
        String query = "SELECT * FROM users WHERE id = '" +
                Integer.toString(userId) + "'";

        return getUserFromQuery(query);
    }


    public User getUser(String userName) {
        String query = "SELECT * FROM users WHERE login = '" +
                userName + "'";

        return getUserFromQuery(query);
    }

    public User getUserFromQuery(String query) {
        Connection connection = DbWrapper.getConnection();

        User user = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();

            user = new User(
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    stringToBoolean(resultSet.getString("gender")),
                    resultSet.getString("phone"),
                    resultSet.getString("dob"),
                    resultSet.getString("city")
            );
            user.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }


    public  User[] getAll() {
        String query = "SELECT * FROM users";
        User user = null;
        ArrayList<User> users = new ArrayList();

        Connection connection = DbWrapper.getConnection();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {

                user = new User(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        stringToBoolean(resultSet.getString("gender")),
                        resultSet.getString("phone"),
                        resultSet.getString("dob"),
                        resultSet.getString("city")
                );
                user.setId(resultSet.getInt("id"));

                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }

        User[] usersArr = new User[users.size()];
        for (int i = 0; i < users.size(); i++) {
            usersArr[i] = users.get(i);
        }

        return usersArr;
    }


    public void deleteUser(int userId) {
        Connection connection = DbWrapper.getConnection();

        String query = "DELETE  FROM users WHERE id = '" +
                Integer.toString(userId) + "'";
        try {
            connection.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }


    public void printAll() {
        User[] users = getAll();
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i].toString());
        }
    }

    private boolean stringToBoolean(String s) {
        return !s.equals("0");
    }

    private String booleanToString(Boolean b) {
        if (b) return "1";
        else return "0";
    }
}
