package com.example.csit228_f1_v2.queries.auth;

import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginQuery {
    public static Integer POST(String username, String password) {

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM user WHERE username = ? LIMIT 1");) {

            statement.setString(1, username);
            ResultSet res = statement.executeQuery();


            if (!res.next()) return 0;

            Integer id = res.getInt("id");
            String hashedPassword = res.getString("password");

            if (password.equals(hashedPassword)) {
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
