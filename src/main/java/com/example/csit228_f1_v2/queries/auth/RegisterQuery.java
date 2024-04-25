package com.example.csit228_f1_v2.queries.auth;

import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterQuery {

    public static Integer POST(String username, String password) throws Exception {
        Integer count = 0;

        try (Connection c = MySQLConnection.getConnection();

             PreparedStatement statement = c.prepareStatement("INSERT INTO user (username, password) VALUES (?,?)");) {

            statement.setString(1, username);
            statement.setString(2, password);

            count = statement.executeUpdate();
            if (count == 0) throw new Exception("No rows were added! An exception occurred!");

        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return count;
    }
}
