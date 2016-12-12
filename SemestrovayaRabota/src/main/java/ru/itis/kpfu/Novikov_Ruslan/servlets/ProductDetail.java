package ru.itis.kpfu.Novikov_Ruslan.servlets;

import ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao;
import ru.itis.kpfu.Novikov_Ruslan.utils.DbWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao.getProduct;


@WebServlet("/product_detail/*")
public class ProductDetail extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = ProductDao.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if(request.getParameter("comment") != null ) {
            int id = Integer.valueOf(request.getPathInfo().substring(1));

            String comment = request.getParameter("comment");

            Connection connection = DbWrapper.getConnection();
            PreparedStatement preparedStatement = null;
            try {
               preparedStatement = connection.prepareStatement("INSERT INTO comments(comment,productid) VALUES(?,?);");
               preparedStatement.setString(1, comment);
               preparedStatement.setInt(2, id);
            } catch (SQLException e) {
               e.printStackTrace();
            }
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                 e.printStackTrace();
            }
            try {
                request.setAttribute("product", getProduct(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                request.setAttribute("comments", getProduct(id).getComments());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletConfig().getServletContext().getRequestDispatcher("/product_detail.ftl").forward(request, response);


        }else{

                int id = Integer.valueOf(request.getPathInfo().substring(1));
            try {
                request.setAttribute("product", getProduct(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                request.setAttribute("comments", getProduct(id).getComments());
            } catch (SQLException e) {
                e.printStackTrace();
            }


            getServletConfig().getServletContext().getRequestDispatcher("/product_detail.ftl").forward(request, response);

        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         super.doPost(request,response);
    }
}
