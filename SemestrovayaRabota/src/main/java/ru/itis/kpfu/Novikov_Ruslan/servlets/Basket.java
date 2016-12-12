package ru.itis.kpfu.Novikov_Ruslan.servlets;

import ru.itis.kpfu.Novikov_Ruslan.DAO.BasketDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/basket")

public class Basket extends HttpServlet {

    private BasketDao basketDao;
    public void init() {
        basketDao = BasketDao.getInstance();
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
        } else {
            try {
                request.setAttribute("basket", basketDao.getAll((String) request.getSession().getAttribute("user")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletConfig().getServletContext().getRequestDispatcher("/basket.ftl").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
           super.doPost(request,response);

    }
}
