package ru.itis.kpfu.Novikov_Ruslan.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.itis.kpfu.Novikov_Ruslan.DAO.ProductDao;
import ru.itis.kpfu.Novikov_Ruslan.DAO.UserDaoImpl;
import ru.itis.kpfu.Novikov_Ruslan.models.Product;
import ru.itis.kpfu.Novikov_Ruslan.models.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


@WebServlet("/admin")

public class Admin extends HttpServlet {
    private int MaxFileSize = 1024 * 1024 * 3;
    private ProductDao productDao;
    static Product product = new Product("",0,"","","",0);
    static String nameOfProduct;

    @Override
    public void init() throws ServletException {
        productDao = ProductDao.getInstance();

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("/");
            return;
        }

        if ("delete".equals(request.getParameter("action"))) {
            System.out.println("deleting!");
            String id = request.getParameter("id");

            if (id != null) {
                try {
                    UserDaoImpl.getInstance().deleteUser(Integer.valueOf(id));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        User[] users = UserDaoImpl.getInstance().getAll();
        request.setAttribute("Users", users);

        getServletConfig().getServletContext().getRequestDispatcher("/admin/admin.ftl").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletContext servletContext = this.getServletConfig().getServletContext();

        factory.setSizeThreshold(1024*1024);

        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(MaxFileSize);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();
            if (iterator.hasNext()) {
                do {
                    FileItem item = (FileItem) iterator.next();
                    if (item.isFormField()) {
                        processFormField(item);
                    } else {
                        processUploadedFile(item);
                    }
                } while (iterator.hasNext());
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
            productDao = ProductDao.getInstance();
            try {
                productDao.addProduct(product);
                product = new Product("",0,"","","",0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        response.sendRedirect("/tov");
    }

    private void processFormField(FileItem item) {
        String fieldName = item.getFieldName();

        switch(fieldName) {
            case "product_name":
                product.setName(item.getString());
                nameOfProduct = item.getString();
                break;
            case "description":
                product.setDescription(item.getString());
                break;
            case "detailed_description":
                product.setDetailed_description(item.getString());
                break;
            case "price":
                product.setPrice(Integer.parseInt(item.getString()));
                break;
            case "quantity":
                product.setQuantity(Integer.parseInt(item.getString()));
        }

    }

    private String processUploadedFile(FileItem item) throws Exception {


        String path = "C:\\Users\\ruslan\\Desktop\\SemestrovayaRabota\\src\\main\\webapp\\imagesOfproducts\\"  + nameOfProduct + ".jpg";
        File uploadedFile = new File(path);

        uploadedFile.createNewFile();

        item.write(uploadedFile);

        return item.getName();
    }
}