package servlets;

import DAO.UserDaoImpl;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by ruslan on 07.10.2017.
 */

@WebServlet("/junior-3/sign-in")

public class SignInServlet extends HttpServlet {

    private static UserService userService;

    public void init(){
         userService = new UserServiceImpl(UserDaoImpl.getInstance());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");


        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if("logout".equals(action)) {

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("testTaskUsername".equals(c.getName())) {
                       c.setMaxAge(0);
                       response.addCookie(c);
                    } else if ("testTaskPassword".equals(c.getName())) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
            }

            Cookie usernameCookie = new Cookie("testTaskUsername", (String) request.getSession().getAttribute("username"));
            Cookie passwordCookie = new Cookie("testTaskPassword",(String) request.getSession().getAttribute("password"));

            usernameCookie.setMaxAge(60*60*24);
            passwordCookie.setMaxAge(60*60*24);

            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);

            session.removeAttribute("user");

            getServletConfig().getServletContext().getRequestDispatcher("/signin.ftl").forward(request,response);

        } else if (session.getAttribute("user") != null) {
                response.sendRedirect("/junior-3/welcome");
        } else if(session.getAttribute("user") == null){
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {

                    for (Cookie c : cookies) {
                        if ("testTaskUsername".equals(c.getName())) {

                            request.getSession().setAttribute("username", c.getValue());

                            c.setMaxAge(0);
                            response.addCookie(c);

                        } else if ("testTaskPassword".equals(c.getName())) {

                            request.getSession().setAttribute("password", c.getValue());

                            c.setMaxAge(0);
                            response.addCookie(c);
                        }

                    }
                }

            Cookie usernameCookie = new Cookie("testTaskUsername", (String) request.getSession().getAttribute("username"));
            Cookie passwordCookie = new Cookie("testTaskPassword",(String) request.getSession().getAttribute("password"));

            usernameCookie.setMaxAge(60*60*24);
            passwordCookie.setMaxAge(60*60*24);

            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);



            request.setAttribute("info","Необходимо ввести учетные данные");
            request.getRequestDispatcher("/signin.ftl").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(userService.isRegistered(username,password)) {

            HttpSession session = request.getSession();
            session.setAttribute("user",username);
            session.setAttribute("username",username);
            session.setAttribute("password",password);

            response.sendRedirect("/junior-3/welcome");

        } else{
            request.setAttribute("error","Имя пользователя и пароль не подходят");
            getServletConfig().getServletContext().getRequestDispatcher("/signin.ftl").forward(request,response);
        }

    }

}
