package com.example.csit228_f1_v2.queries.product;

import com.example.csit228_f1_v2.classes.Product;
import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductQuery {


    public static List<Product> GET() {

        List<Product> products = new ArrayList<>();

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM product");) {

            ResultSet res = statement.executeQuery();

            while (res.next()) {


                Integer id = res.getInt("id");
                Integer sellerId = res.getInt("seller_id");
                String name = res.getString("name");
                String description = res.getString("description");
                Integer quantity = res.getInt("quantity");


                products.add(new Product(id, sellerId, name, description, quantity));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public static Integer POST(Integer sellerId, String name, String description, String quantity) {


        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("INSERT INTO product(sellerId, name, description, quantity) VALUES(?, ?, ?, ?)");) {

            statement.setInt(1, sellerId);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, quantity);

            Integer res = statement.executeUpdate();
            System.out.println("Products inserted: " + res);

            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Integer UPDATE(Integer id, Integer sellerId, String name, String description, String quantity) {


        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("UPDATE product SET sellerId = ?, name = ?, description = ?, quantity = ? WHERE id = ?");) {

            statement.setInt(1, sellerId);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, quantity);
            statement.setInt(5, id);

            Integer res = statement.executeUpdate();
            System.out.println("Products updated: " + res);

            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Integer DELETE(Integer id) {


        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("DELETE FROM product WHERE id = ?");) {

            statement.setInt(0, id);

            Integer res = statement.executeUpdate();
            System.out.println("Products deleted: " + res);

            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}

