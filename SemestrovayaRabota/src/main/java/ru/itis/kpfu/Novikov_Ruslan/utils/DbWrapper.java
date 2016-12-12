package ru.itis.kpfu.Novikov_Ruslan.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbWrapper {
    private final static String DRIVER = "org.postgresql.Driver";
    private final static String CONNECTION_URI = "jdbc:postgresql://localhost:12345/hockey_shop";
    private final static String LOGIN = "postgres";
    private final static String PASSWORD = "123456";

    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            try{
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(CONNECTION_URI, LOGIN, PASSWORD);
            }
            catch(ClassNotFoundException ex){
               System.out.println("Can't find DB driver.");
            } catch (SQLException ex) {
                System.out.println("Can't connect to DB (" + ex.getErrorCode() + ": " + ex.getMessage() + ").");
            }

        }
        return connection;
    }
}
