package ru.itis.kpfu.Novikov_Ruslan.servlets;

import ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/getproductsnext/*")
class GetProductsNext extends HttpServlet {

    private ProductDao productDao;

    @Override
    public void init() {
       productDao = ProductDao.getInstance();
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
         super.doPost(request,response);

    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         try{
              int id = Integer.valueOf(request.getPathInfo().substring(1));
              request.setAttribute("productsDecade",productDao.getDecadeProduct(id));
          } catch(SQLException e) {
              e.printStackTrace();
          }
          getServletConfig().getServletContext().getRequestDispatcher("get_products_next.ftl").forward(request,response);
    }




}
