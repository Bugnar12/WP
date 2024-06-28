package controller;

import DB.DBManager;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    public static synchronized User createUserWithLoggedPlayers(String username, int playersLogged) {
        String sql = "Select * from Users where username = ?";
        User user = new User();
        user.setPlayerNumber(playersLogged);
        int id = -1;
        try {
            PreparedStatement stmt = DBManager.getConnection().prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id != -1) {
            sql = "insert into playerslogged(userID) values(?)";
            try {
                PreparedStatement stmt = DBManager.getConnection().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return user;
    }
}
