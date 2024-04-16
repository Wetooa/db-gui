package com.example.csit228_f1_v2.queries.auth;

import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterQuery {

    public static Integer POST(String username, String password) {


        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("INSERT INTO user (username, password) VALUES (?,?)");){

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet res = statement.executeQuery();

            System.out.println("User registered successfully!");
            Integer id = res.getInt("id");

            return id;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
