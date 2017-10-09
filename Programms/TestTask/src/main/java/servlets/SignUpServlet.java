package servlets;

import DAO.UserDao;
import DAO.UserDaoImpl;
import services.UserService;
import services.UserServiceImpl;
import utils.FormDataCheck;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Created by ruslan on 07.10.2017.
 */

@WebServlet("/junior-3/sign-up")

public class SignUpServlet extends HttpServlet {

    UserService userService;
    UserDao userDao;

    public void init(){
        userDao = UserDaoImpl.getInstance();
        userService = new UserServiceImpl(userDao);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            response.sendRedirect("/junior-3/welcome");
        } else {
            response.sendRedirect("/signup.ftl");
        }

      }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");


        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();

        if(FormDataCheck.isValidName(username) && FormDataCheck.isValidPassword(password)
                && FormDataCheck.passwordsEquality(password,confirmPassword) &&
                !FormDataCheck.isExists(username)){
            System.out.println("LET'S REGISTER");
            userService.register(username,password);

            HttpSession session = request.getSession();
            session.setAttribute("user",username);
            session.setAttribute("username",username);
            session.setAttribute("password",password);

            response.sendRedirect("/junior-3/welcome");

        } else {

            if (!FormDataCheck.isValidName(username)) {
                request.setAttribute("falseusername", "Имя пользователя должно быть длинее 4 символов и состоять из цифр и букв английского алфавита");
            }
            if (!FormDataCheck.isValidPassword(password)) {
                request.setAttribute("falsepassword", "Пароль недостаточно сложен:должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов");
            }
            if (!FormDataCheck.passwordsEquality(password, confirmPassword)) {
                request.setAttribute("passwordsequality", "Пароль и повтор пароля не совпадают");
            }
            if(FormDataCheck.isExists(username)) {
                request.setAttribute("existsUser","Пользователь с таким именем уже зарегистрирован");
            }


            request.setAttribute("usernameEntered", username);
            request.setAttribute("passwordEntered", password);
            request.setAttribute("confirmPasswordEntered", confirmPassword);
            request.getRequestDispatcher("/signup.ftl").forward(request, response);
        }


    }

}
