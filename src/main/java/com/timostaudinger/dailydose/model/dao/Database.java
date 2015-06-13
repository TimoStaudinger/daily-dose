package com.timostaudinger.dailydose.model.dao;

import com.timostaudinger.dailydose.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

class Database {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        String userName = Properties.get("database_user");
        String password = Properties.get("database_password");
        String url = Properties.get("database_url");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO: logging
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            // TODO: logging
            e.printStackTrace();
        }
    }
}
