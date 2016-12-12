package ru.itis.kpfu.Novikov_Ruslan.servlets;


import ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao;
import ru.itis.kpfu.Novikov_Ruslan.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet("/tov")



public class Tovary extends HttpServlet {
    ProductDao productDao  = ProductDao.getInstance();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if("get_products".equals(request.getParameter("action"))){
            if (request.getSession().getAttribute("admin") != null) {
                request.setAttribute("admin","admin");
            }
            Product[] allproducts = null;

            try {
                 allproducts = productDao.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (allproducts.length > 10 ) {
                ArrayList<Product> productsDecade = new ArrayList();

                for(int i = 0 ; i < 10;i++) {
                    productsDecade.add(allproducts[i]);
                }
                request.setAttribute("productsDecade",productsDecade);
                ArrayList<Product> nextProducts = new ArrayList();

                for(int j = 10 ; j < allproducts.length;j++) {
                    nextProducts.add(allproducts[j]);
                }

                request.setAttribute("nextProducts",nextProducts);
            } else {
                 request.setAttribute("productsDecade",allproducts);
            }

            getServletConfig().getServletContext().getRequestDispatcher("/get_products_ajax.ftl").forward(request, response);
            return;
        }

        if("delete".equals(request.getParameter("action"))){
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                productDao.deleteProduct(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return;
        }

        getServletConfig().getServletContext().getRequestDispatcher("/tov.ftl").forward(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        super.doPost(request,response);
    }
}
