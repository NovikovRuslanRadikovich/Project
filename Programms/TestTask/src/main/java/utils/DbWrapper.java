package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ruslan on 07.10.2017.
 */

public class DbWrapper {

    private final static String DRIVER = "org.h2.Driver";
    private final static String CONNECTION_URL = "jdbc:h2:~/test";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null) {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
        }

        return connection;
    }

 }
