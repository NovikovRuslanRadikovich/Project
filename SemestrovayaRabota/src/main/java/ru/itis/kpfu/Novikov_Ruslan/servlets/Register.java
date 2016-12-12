package ru.itis.kpfu.Novikov_Ruslan.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.itis.kpfu.Novikov_Ruslan.DAO.UserDao;
import ru.itis.kpfu.Novikov_Ruslan.DAO.UserDaoImpl;
import ru.itis.kpfu.Novikov_Ruslan.models.User;
import ru.itis.kpfu.Novikov_Ruslan.utils.FormDataCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.TreeMap;



@WebServlet("/register")

public class Register extends HttpServlet {

    UserDao dao;

    @Override
    public void init() throws ServletException {
        dao = UserDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            response.sendRedirect("/");
            return;
        }

        request.getRequestDispatcher("/unregistered/register.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String city = request.getParameter("city");
        String sex = request.getParameter("sex");

        TreeMap message = FormDataCheck.checkAllFieldsAndGetErrorMessageIfFieldsAreInvalid(username, phone, dob, password, password2, sex, city);

        PrintWriter pw = response.getWriter();

        if (message.isEmpty()) {
            boolean isMale = sex.equals("male");
            User user = new User(username, password, isMale, phone, dob, city);
            try {

                dao.saveUser(user);

                request.getSession().setAttribute("user", username);


                pw.write("success");



            } catch (SQLException e) {

                e.printStackTrace();
                pw.write("SQL exception!");
                pw.write(e.getMessage());
            }


        } else {

            ObjectMapper mapper = new ObjectMapper();

            String jsonString = mapper.writeValueAsString(message);

            pw.write(jsonString);
        }

        pw.close();
    }

}


