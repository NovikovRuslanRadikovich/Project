package ru.itis.kpfu.Novikov_Ruslan.DAO;

import ru.itis.kpfu.Novikov_Ruslan.models.Product;
import ru.itis.kpfu.Novikov_Ruslan.utils.DbWrapper;

import java.sql.*;
import java.util.ArrayList;


public class BasketDao {
    private static BasketDao feedItemDao;

    public static BasketDao getInstance() {
        if (feedItemDao == null) {
            feedItemDao = new BasketDao();
        }
        return feedItemDao;
    }


    private BasketDao() {

    }

    public void addProduct(String BasketName, Product item) throws SQLException {
        Connection connection = DbWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"" + BasketName + "\" (id) VALUES (?);");

            preparedStatement.setInt(1, item.getId());

            preparedStatement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public Product getProduct(String BasketName, int productId) throws SQLException {
        String query = "SELECT * FROM \"" + BasketName + "\"" + " WHERE id = '" +
                Integer.toString(productId) + "'";
        Connection connection = DbWrapper.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);

        return getProductFromQuery(query);
    }
    public Product[] getAll(String BasketName) throws SQLException {
        String query = "SELECT * FROM products INNER JOIN \"" + BasketName + "\" on(products.id = \"" + BasketName + "\".id)";
        Connection connection = DbWrapper.getConnection();
        ArrayList<Product> products = new ArrayList();
        ResultSet resultSet = connection.createStatement().executeQuery(query);

        while(resultSet.next()) {
            Product item = null;
            int id = resultSet.getInt("id");
            item = ProductDao.getProduct(id);

                products.add(item);

        }
        Product[] productsArr = new Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productsArr[i] = products.get(i);
        }

        return productsArr;
    }
    private Product getProductFromQuery(String query) throws SQLException {
        Connection connection = DbWrapper.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        Product item = null;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            item = ProductDao.getProduct(id);
        }
        return item;
    }

    public void deleteProduct(String BasketName, int Id) {
        Connection connection = DbWrapper.getConnection();

        String query = "DELETE FROM \"" + BasketName + "\" WHERE id = '" +
                Integer.toString(Id) + "'";
        try {
            connection.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }


}

