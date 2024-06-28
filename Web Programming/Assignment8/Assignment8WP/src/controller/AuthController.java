package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DB.DBManager;

public class AuthController {
    public static String authenticate(String username, String password) {
        ResultSet rs;
        String result = "";
        DBManager.connect();
        Connection conn = DBManager.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = "Login successful.";
            } else {
                result = "Login failed.";
            }
            rs.close();
            DBManager.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}