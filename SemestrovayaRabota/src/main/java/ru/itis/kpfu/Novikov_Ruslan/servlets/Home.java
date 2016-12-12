package ru.itis.kpfu.Novikov_Ruslan.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/home")



public class Home extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if ("logout".equals(request.getParameter("action"))) {
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("admin");

           for(Cookie cookie : request.getCookies()) {
               if("users".equals(cookie.getName())) {
                   cookie.setMaxAge(0);
                   response.addCookie(cookie);
               }

           }
            response.sendRedirect("/login");
            return;
        }

        for(Cookie cookie : request.getCookies()) {
            if("users".equals(cookie.getName())) {
                request.getSession().setAttribute("user", URLDecoder.decode(cookie.getValue(),"UTF-8"));
            }
        }

        getServletConfig().getServletContext().getRequestDispatcher("/home.ftl").forward(request, response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        super.doPost(request,response);
    }
}
