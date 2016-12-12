package ru.itis.kpfu.Novikov_Ruslan.models;


import ru.itis.kpfu.Novikov_Ruslan.utils.DbWrapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product {

    private int id;
    public String name;
    private int price;
    public String description;
    public String detailed_description;
    private int quantity;
    private String imagePath;

    public Product(String name, int price, String image, String description,String detailed_description, int quantity) {
        this.name = name;
        this.price = price;
        this.imagePath = image;
        this.description = description;
        this.detailed_description = detailed_description;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {this.description = description; }

    public void setDetailed_description(String detailed_description) {this.detailed_description = detailed_description;}

    public void setPrice(int price) {this.price = price;}

    public void setName(String name) {this.name = name;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailed_description() {return detailed_description;}



    public int getQuantity() {
        return quantity;
    }

    public String getImagePath() {return imagePath;}

    public String[] getComments() {
        int id = getId();
        ArrayList<String> comments = new ArrayList();
        String query = "SELECT * FROM comments WHERE productid = '" + id + "'";
        Connection connection = DbWrapper.getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next()) {
                String comment = resultSet.getString("comment");
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] commentaries = new String[comments.size()];
        for (int i = 0; i < comments.size(); i++) {
            commentaries[i] = comments.get(i);
        }

        return commentaries;

    }
}


