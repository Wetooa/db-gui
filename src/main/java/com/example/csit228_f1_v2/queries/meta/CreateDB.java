package com.example.csit228_f1_v2.queries.meta;


import com.example.csit228_f1_v2.db.MySQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    public static void main(String[] args) {
        Connection c = MySQLConnection.getConnection();
        StringBuilder sb = new StringBuilder();

//        sb.append(
//                "CREATE TABLE IF NOT EXISTS user (" +
//                        "id INT PRIMARY KEY AUTO_INCREMENT," +
//                        "username TEXT NOT NULL," +
//                        "password TEXT NOT NULL" +
//                        ");"
//        );

//        sb.append(
//                "CREATE TABLE IF NOT EXISTS product (" +
//                        "id INT PRIMARY KEY AUTO_INCREMENT," +
//
//                        "sellerId INT," +
//
//                        "name TEXT NOT NULL," +
//                        "description TEXT," +
//                        "quantity INT DEFAULT 0" +
//                        ");"
//        );

//        sb.append(
//                "ALTER TABLE product " +
//                        "ADD CONSTRAINT FK_sellerId FOREIGN KEY(sellerId) " +
//                        "REFERENCES user(id);"
//        );


        try {
            Statement statement = c.createStatement();
            statement.execute(sb.toString());
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
