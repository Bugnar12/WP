package com.example.demo.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBManager {
    private static Connection connection;

    public static void connect() {
        if (connection == null) {
            String url = "jdbc:sqlserver://DESKTOP-Q7V5AP;databaseName=Assignment8_WP;integratedSecurity=true;";

            Properties props = new Properties();
            try {
                FileInputStream in = new FileInputStream("db.properties");
                props.load(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }
    public static Connection getConnection() {
        if(connection == null) {
            connect();
        }
        return connection;
    }

}