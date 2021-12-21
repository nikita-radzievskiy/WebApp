package com.radz.webapp.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    public PreparedStatement pstmt;
    public ResultSet rs;


    public static Connection getConnection() {

        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/task4db";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "12345678");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
