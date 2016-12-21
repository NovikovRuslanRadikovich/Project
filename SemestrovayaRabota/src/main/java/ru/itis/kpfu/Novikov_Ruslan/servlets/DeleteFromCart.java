package ru.itis.kpfu.Novikov_Ruslan.servlets;

import ru.itis.kpfu.Novikov_Ruslan.DAO.BasketDao;
import ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteFromCart/*")
public class DeleteFromCart extends HttpServlet {
    BasketDao basketDao;
    ProductDao productDao;
    public void init() throws ServletException{
        basketDao = BasketDao.getInstance();
        productDao = ProductDao.getInstance();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getPathInfo().substring(1));
        System.out.println(id);
        String Basket = (String) request.getSession().getAttribute("user");
        System.out.println(Basket);

        BasketDao.getInstance().deleteProduct(Basket,id);



    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
         super.doPost(request,response);
    }
}

