package ru.itis.kpfu.Novikov_Ruslan.servlets;

import ru.itis.kpfu.Novikov_Ruslan.DAO.BasketDao;
import ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao;
import ru.itis.kpfu.Novikov_Ruslan.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addToCart/*")
public class AddToCart extends HttpServlet {
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

        String Basket = (String) request.getSession().getAttribute("user");

        try {
            Product product = ProductDao.getProduct(id);
            BasketDao.getInstance().addProduct(Basket,product);
        } catch(SQLException e){
            System.out.println("SQL error");
            return;
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
          super.doPost(request,response);
    }
}
