package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBManager {
    private static Connection connection;

    public static void connect() {
        if (connection == null) {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment8_WP;integratedSecurity=true;loginTimeout=30";

            Properties props = new Properties();
            try {
                InputStream in = DBManager.class.getClassLoader().getResourceAsStream("db.properties");
                props.load(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException | ClassNotFoundException e) {
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

    public static Connection getConnectionWithoutConnect() {
        return connection;
    }

}