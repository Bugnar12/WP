package servlets;

import DB.DBManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    @Override
    public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkNoOfPlayersLogged();
        User user = (User)request.getSession().getAttribute("user");
        deleteLoggedUser(user.getUsername());
        response.sendRedirect("/index.jsp");
    }

    private void checkNoOfPlayersLogged(){
        String stmt = "select * from playerslogged";
        try {
            PreparedStatement preparedStmt = DBManager.getConnection().prepareStatement(stmt);
            ResultSet rs = preparedStmt.executeQuery();
            int playersLoggedIn = -1;
            if (rs.next()) ///while we still have logged in players
            {
                playersLoggedIn = rs.getInt("NoOfPlayers");
                int newNoOfPlayers = playersLoggedIn - 1; //decrement player number
                if(newNoOfPlayers <= 0)
                {
                    DestroyBoard();
                    newNoOfPlayers = 0;
                }
                String updateStmt = "update playerslogged set NoOfPlayers = ?";
                PreparedStatement updatePreparedStmt = DBManager.getConnection().prepareStatement(updateStmt);
                updatePreparedStmt.setInt(1, newNoOfPlayers);
                updatePreparedStmt.execute();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void DestroyBoard()
    {
        String stmt = "Update boardstate set boardstate='---------', ActivePlayer=0";
        try{
            PreparedStatement preparedStmt = DBManager.getConnection().prepareStatement(stmt);
            preparedStmt.execute();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deleteLoggedUser(String username)
    {
        String stmt = "select * from Users where username = ?";
        int id = -1;
        try{
            PreparedStatement preparedStmt = DBManager.getConnection().prepareStatement(stmt);
            preparedStmt.setString(1, username);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(id != -1)
        {
            String deleteStmt = "delete from playerslogged where userID = ?";
            try{
                PreparedStatement preparedStmt = DBManager.getConnection().prepareStatement(deleteStmt);
                preparedStmt.setInt(1, id);
                preparedStmt.execute();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
