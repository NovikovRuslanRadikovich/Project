package ru.itis.kpfu.Novikov_Ruslan.servlets;

import ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao;
import ru.itis.kpfu.Novikov_Ruslan.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/edit_product/*")
public class EditProduct extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getPathInfo().substring(1));
        try {
            Product product = ProductDao.getProduct(id);
            request.setAttribute("product",product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getServletConfig().getServletContext().getRequestDispatcher("/edit_product.ftl").forward(request,response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String detailed_description = request.getParameter("detailed_description");
        int price = Integer.parseInt(request.getParameter("price"));
        int id = Integer.valueOf(request.getPathInfo().substring(1));
        try{
            ProductDao productDao = ProductDao.getInstance();
            productDao.editProduct(id,price,detailed_description);
            response.sendRedirect("/tov");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
