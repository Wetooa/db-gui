package com.example.csit228_f1_v2.queries.meta;


import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateDB {
    public static void createDB() {
        Connection c = MySQLConnection.getConnection();
        List<String> queries = new ArrayList<>();

        queries.add(
                "CREATE TABLE IF NOT EXISTS user (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        "username TEXT NOT NULL UNIQUE," +
                        "password TEXT NOT NULL" +
                        ");"
        );

        queries.add(
                "CREATE TABLE IF NOT EXISTS post (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +

                        "posterId INT," +

                        "message TEXT NOT NULL," +

                        "createdAt DATETIME DEFAULT CURRENT_TIMESTAMP," +
                        "updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                        ");"
        );

        queries.add(
                "ALTER TABLE post " +
                        "ADD CONSTRAINT FK_posterId FOREIGN KEY(posterId) " +
                        "REFERENCES user(id) " +
                        "ON DELETE CASCADE;"
        );

        try {
            Statement statement = c.createStatement();

            for (String query : queries) {
                statement.execute(query);
            }

            System.out.println("Tables created successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
