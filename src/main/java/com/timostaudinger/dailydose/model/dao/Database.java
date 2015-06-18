package com.timostaudinger.dailydose.model.dao;

import com.timostaudinger.dailydose.util.Properties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

class Database {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String userName = Properties.get("database_user");
            String password = Properties.get("database_password");
            String url = Properties.get("database_url");

            PoolProperties p = new PoolProperties();
            p.setUrl(url);
            p.setDriverClassName("com.mysql.jdbc.Driver");
            p.setUsername(userName);
            p.setPassword(password);

            DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource(p);

            return datasource.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: logging
            e.printStackTrace();
        }
        return null;
    }
}
