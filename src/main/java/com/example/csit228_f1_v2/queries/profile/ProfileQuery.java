package com.example.csit228_f1_v2.queries.profile;

import com.example.csit228_f1_v2.classes.User;
import com.example.csit228_f1_v2.data.Storage;
import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileQuery {

    public static User GET(Integer id) throws Exception {

        User user = null;
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM user WHERE id = ?");) {

            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            if (!res.next()) throw new Exception("User not found!");

            String username = res.getString("username");
            user = new User(id, username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw e;
        }

        return user;
    }


    public static User UPDATE(String username, String password) throws Exception {

        User user = null;
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("UPDATE user SET username = ?, password = ? WHERE id = ?");) {

            Integer id = Storage.user.getId();

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, id);

            Integer res = statement.executeUpdate();

            if (res == 0) throw new Exception("User not found!");

            user = new User(id, username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw e;
        }

        return user;
    }

    public static void DELETE() throws Exception {

        User user = null;
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("DELETE user WHERE id = ?");) {

            Integer id = Storage.user.getId();
            statement.setInt(1, id);

            ResultSet res = statement.executeQuery();

            if (!res.next()) throw new Exception("User not found!");

            Storage.user = null;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw e;
        }
    }
}
