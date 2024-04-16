package com.example.csit228_f1_v2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/dbsajulgagui";
    public static final String USER = "root";
    public static final String PASS = "";


    public static Connection getConnection() {

        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("DB Connection Success");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return c;
    }


    public static void main(String[] args) {
        Connection c = MySQLConnection.getConnection();


        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
