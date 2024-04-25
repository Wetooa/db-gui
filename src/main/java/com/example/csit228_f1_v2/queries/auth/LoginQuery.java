package com.example.csit228_f1_v2.queries.auth;

import com.example.csit228_f1_v2.classes.User;
import com.example.csit228_f1_v2.data.Storage;
import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginQuery {
    public static Integer POST(String username, String password) throws Exception {
        Integer id;

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM user WHERE username = ? LIMIT 1");) {

            statement.setString(1, username);
            ResultSet res = statement.executeQuery();

            if (!res.next()) throw new Exception("Invalid credentials");

            id = res.getInt("id");
            String hashedPassword = res.getString("password");

            if (!password.equals(hashedPassword))
                throw new Exception("Passwords don't match!");

            Storage.user = new User(id, username);
        } catch (SQLException e) {
            // TODO: i dunno improve this bad code
            throw e;
        } catch (Exception e) {
            throw e;
        }

        return id;
    }

}
