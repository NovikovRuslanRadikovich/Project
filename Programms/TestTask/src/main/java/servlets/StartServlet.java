package servlets;



import org.apache.log4j.Logger;
import utils.DbWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;


/**
 * Created by ruslan on 07.10.2017.
 */

@WebServlet("/home")

public class StartServlet extends HttpServlet {

    Logger logger = Logger.getLogger(StartServlet.class);

    public void init(){
        logger.error("Таблица уже существует");
        try {
            Connection connection = DbWrapper.getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USERS " +
                                  " (username VARCHAR(255), " +
                                   " password VARCHAR(255))";

            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

          response.sendRedirect("/junior-3/welcome");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }
}
