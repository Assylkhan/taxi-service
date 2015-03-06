package com.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("database");
    private String url;
    private String user;
    private String password;

    public ConnectionFactory() {
        String driver = bundle.getString("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("unable to find driver in classpath ", e);
        }
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
    }

    public ConnectionFactory(String url, String user, String password) {
        String driver = bundle.getString("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("unable to find driver in classpath ", e);
        }
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
