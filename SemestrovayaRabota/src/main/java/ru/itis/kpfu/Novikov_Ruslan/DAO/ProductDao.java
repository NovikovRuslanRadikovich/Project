package ru.itis.kpfu.Novikov_Ruslan.DAO;

import ru.itis.kpfu.Novikov_Ruslan.models.Product;
import ru.itis.kpfu.Novikov_Ruslan.utils.DbWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    private static ProductDao feedItemDao;

    public static ProductDao getInstance() {
        if (feedItemDao == null) {
            feedItemDao = new ProductDao();
        }

        return feedItemDao;
    }

    private ProductDao() {

    }

    public void addProduct(Product item) throws SQLException {
        Connection connection = DbWrapper.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO products (name, description, detailed_description, image, price, quantity) VALUES (?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getDetailed_description());
            preparedStatement.setString(4, item.getImagePath());
            preparedStatement.setInt(5, item.getPrice());
            preparedStatement.setInt(6, item.getQuantity());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.execute();
    }

    public static Product getProduct(int productId) throws SQLException {
        String query = "SELECT * FROM products WHERE id = '" +
                productId + "'";

        return getProductFromQuery(query);
    }

    public static Product getProduct(String productName) throws SQLException {
        String query = "SELECT * FROM products WHERE name = '" + productName + "'";

        return getProductFromQuery(query);
    }
    public Product[] getDecadeProduct(int decadeId) throws SQLException {
          Product[] allproducts = getAll();
          int productsNumber = allproducts.length;
          List<Product> decadeProducts = new ArrayList();
          for(int k = productsNumber-1-10*(decadeId - 1); k > productsNumber-1-10*(decadeId);k--) {
              if(k >= 0) {
                  decadeProducts.add(allproducts[k]);
              }
          }
          Product[] decade = new Product[decadeProducts.size()];
          for (int i = 0; i < decadeProducts.size(); i++) {
             decade[i] = decadeProducts.get(i);
          }

          return decade;
    }

    public static Product getProductFromQuery(String query) throws SQLException {
        Connection connection = DbWrapper.getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery(query);
        resultSet.next();

        Product item = new Product(
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("image"),
                resultSet.getString("description"),
                resultSet.getString("detailed_description"),
                resultSet.getInt("quantity")
        );

        item.setId(resultSet.getInt("id"));
            return item;

    }

    public void editProduct(int productId,int price,String detailed_description) throws SQLException {
        Connection connection = DbWrapper.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE products SET price = ?, detailed_description = ? WHERE id = '" + productId + "'");
            preparedStatement.setInt(1,price);
            preparedStatement.setString(2,detailed_description);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.execute();
    }
    public void deleteProduct(int productId) throws SQLException {
        Connection connection = DbWrapper.getConnection();

        String query = "DELETE FROM products WHERE id = '" +
                Integer.toString(productId) + "'";
        try {
            connection.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }

    }

    public Product[] getAll() throws SQLException {
        String query = "SELECT * FROM products ORDER BY id DESC";

        return getProductsFromQuery(query);
    }


    private Product[] getProductsFromQuery(String query) throws SQLException{
        ArrayList<Product> products = new ArrayList();

        Connection connection = DbWrapper.getConnection();

        Product[] productsArr;
        ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {

                Product item = new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("image"),
                        resultSet.getString("description"),
                        resultSet.getString("detailed_description"),
                        resultSet.getInt("quantity")
                );
                item.setId(resultSet.getInt("id"));

                products.add(item);
            }


        productsArr = new Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productsArr[i] = products.get(i);
        }

        return productsArr;
    }

}
